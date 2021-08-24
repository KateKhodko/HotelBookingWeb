package com.khodko.RoyalHotel.dao.core;

import com.khodko.RoyalHotel.connection.ProxyConnection;
import com.khodko.RoyalHotel.dao.schema.Table;
import com.khodko.RoyalHotel.model.DbEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class BaseDao<T extends DbEntity> implements Dao<T> {

    private static final String FIND_ALL_SQL = "SELECT %s FROM %s";
    private static final String FIND_BY_ID_SQL = "SELECT %s FROM %s WHERE id = ?";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM %s WHERE id = ?";        
    private static final String COUNT_SQL = "SELECT COUNT(*) FROM %s";

    private final ProxyConnection connection;
    protected final Mapper<T> mapper;
    private final String tableName;
    private final String columns;
          
    protected BaseDao(ProxyConnection connection, Table<T> table, Mapper<T> mapper) {
        this.connection = connection;
        this.mapper = mapper;
        this.tableName = table.getName();
        this.columns = String.join(", ", table.getColumns());       
    }  
        
    @Override
    public List<T> findAll() throws SQLException {
    	String query = String.format(FIND_ALL_SQL, columns, tableName);
        return executeQuery(query);
    }

    @Override
    public Optional<T> findById(Integer id) throws SQLException {
    	String query = String.format(FIND_BY_ID_SQL, columns, tableName);
        return executeSingleResultQuery(query, id);       
    }
    
    @Override
    public T save(T entity) throws SQLException {
        return null;
    }

    @Override
    public T update(T entity) throws SQLException {
        return null;
    }
        
    @Override
    public void delete(Integer id) throws SQLException {
    	String query = String.format(DELETE_BY_ID_SQL, tableName);
        executeUpdate(query, id);
    }
    
    @Override
    public int count() throws SQLException {
    	String query = String.format(COUNT_SQL, tableName);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet.next() ? resultSet.getInt(1) : 0;
    }
                             
    private PreparedStatement createStatement(String query, Object... parameters) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        for (int i = 0, length = parameters.length; i < length; i++) {
            statement.setObject(i + 1, parameters[i]);
        }
        return statement;
    }
    
    protected List<T> executeQuery(String query, Mapper<T> mapper, Object... parameters) throws SQLException {
        PreparedStatement statement = createStatement(query, parameters);
        ResultSet resultSet = statement.executeQuery();
        List<T> entityElements = new ArrayList<>();
        while (resultSet.next()) {
            T entityElement = mapper.map(resultSet);
            entityElements.add(entityElement);
        }
        return entityElements;      
    }
    
    protected Optional<T> executeSingleResultQuery(String query, Object ... parameters) throws SQLException {
        return executeSingleResultQuery(query, mapper, parameters);
    }
    
    protected Optional<T> executeSingleResultQuery(String query, Mapper<T> mapper, Object ... parameters) throws SQLException {
        List<T> entityElements = executeQuery(query, mapper, parameters);
        int size = entityElements.size();
        if (size > 1) {
            throw new SQLException("More than one result: " + size);
        }
        if (size > 0) {
            return Optional.of(entityElements.get(0));
        }
        return Optional.empty();
    }
    
    protected boolean existsQuery(String query, Object... parameters) throws SQLException {
        PreparedStatement statement = createStatement(query, parameters);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();    
    }
    
    protected int countQuery(String query, Object... parameters) throws SQLException {
        PreparedStatement statement = createStatement(query, parameters);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next() ? resultSet.getInt(1) : 0;
    }
    
    protected List<T> executeQuery(String query, Object... parameters) throws SQLException {
    	return executeQuery(query, mapper, parameters);    	           
    }
    
    protected int executeUpdate(String query, Object... parameters) throws SQLException {
    	PreparedStatement statement = createStatement(query, parameters);
        return statement.executeUpdate();       
    }
    
    protected void update(String query, Object... parameters) throws SQLException {
    	int result = executeUpdate(query, parameters);
    	if (result == 0) {
        	throw new SQLException("Updating failed, no rows affected");
        }
    }
       
    /**
     * Save and return generated key;
     */
    protected int save(String query, Object... parameters) throws SQLException {
    	PreparedStatement statement = executeSave(query, parameters);   	       
    	ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        } 
        throw new SQLException("Saving failed, key does not generated");
    }
    
    protected void saveNotKey(String query, Object... parameters) throws SQLException {
    	executeSave(query, parameters);           
    }
    
    protected PreparedStatement executeSave(String query, Object... parameters) throws SQLException {
    	PreparedStatement statement = createStatement(query, parameters);
    	int affectedRows = statement.executeUpdate();  
        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        } 
        return statement;
    }
                 
}

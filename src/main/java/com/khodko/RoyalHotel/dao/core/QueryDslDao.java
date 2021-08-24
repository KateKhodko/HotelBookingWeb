package com.khodko.RoyalHotel.dao.core;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import com.khodko.RoyalHotel.connection.ProxyConnection;
import com.khodko.RoyalHotel.dao.querydsl.Delete;
import com.khodko.RoyalHotel.dao.querydsl.Insert;
import com.khodko.RoyalHotel.dao.querydsl.Query;
import com.khodko.RoyalHotel.dao.querydsl.Update;
import static com.khodko.RoyalHotel.dao.querydsl.where.Where.*;
import com.khodko.RoyalHotel.dao.schema.Table;
import com.khodko.RoyalHotel.model.DbEntity;
import com.khodko.RoyalHotel.model.IdEntity;


public abstract class QueryDslDao<T extends DbEntity> extends BaseDao<T> {
	
	protected Table<T> table;

	protected QueryDslDao(ProxyConnection connection, Table<T> table, Mapper<T> mapper) {
		super(connection, table, mapper);
		this.table = table;
	}
		
	@Override
	public T save(T entity) throws SQLException {  
		Query query = new Insert(table, table.getSaveMap(entity));		
		if (entity instanceof IdEntity) {
			IdEntity idEntity = (IdEntity) entity;
			idEntity.setId(save(query));
		} else {
			saveNotKey(query);
		}
		return entity;		 	
    }
	
	@Override
    public T update(T entity) throws SQLException {   
		if (entity instanceof IdEntity) {
			IdEntity idEntity = (IdEntity) entity;
			Query query = new Update(table, table.getSaveMap(entity)).where(
					eq(table.getName() + ".id", idEntity.getId()));
	    	update(query);
	    	return entity;
		} 
		throw new SQLException("Entity has not id column.");    	
    }	
    				
	@Override
    public void delete(Integer id) throws SQLException {
		Query query = new Delete(table).where(eq("id", id));
		delete(query);   	
    }
	
	protected int save(Query insert) throws SQLException {
    	String query = insert.getQuery();
    	Object[] parameters = insert.getParameters(); 
    	return save(query, parameters);
    }
	
	protected void saveNotKey(Query insert) throws SQLException {
		String query = insert.getQuery();
    	Object[] parameters = insert.getParameters();
    	saveNotKey(query, parameters);         
    }
	
	protected void update(Query update) throws SQLException {
		String query = update.getQuery();
    	Object[] parameters = update.getParameters();
    	update(query, parameters);   	
    }
	
	protected void delete(Query delete) throws SQLException {
    	String query = delete.getQuery();
    	Object[] parameters = delete.getParameters();  	
        executeUpdate(query, parameters);
    }
	
	protected List<T> executeQuery(Query select) throws SQLException {
		return executeQuery(select, mapper);
	}
	
	protected List<T> executeQuery(Query select, Mapper<T> mapper) throws SQLException {
		String query = select.getQuery();
    	Object[] parameters = select.getParameters();
    	return executeQuery(query, mapper, parameters);
	}
	
	protected Optional<T> executeSingleResultQuery(Query select) throws SQLException {
		return executeSingleResultQuery(select, mapper);
	}
	
	protected Optional<T> executeSingleResultQuery(Query select, Mapper<T> mapper) throws SQLException {
		String query = select.getQuery();
    	Object[] parameters = select.getParameters();
    	return executeSingleResultQuery(query, mapper, parameters);
	}
	
	protected boolean existsQuery(Query select) throws SQLException {
		String query = select.getQuery();
    	Object[] parameters = select.getParameters();		        
        return existsQuery(query, parameters);    
    }
	
	protected int countQuery(Query select) throws SQLException {
		String query = select.getQuery();
    	Object[] parameters = select.getParameters();
    	return countQuery(query, parameters);		
    }
	
}

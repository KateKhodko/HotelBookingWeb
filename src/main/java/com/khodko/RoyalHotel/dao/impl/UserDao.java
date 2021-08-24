package com.khodko.RoyalHotel.dao.impl;

import java.sql.SQLException;
import java.util.Optional;

import com.khodko.RoyalHotel.connection.ProxyConnection;
import com.khodko.RoyalHotel.dao.UserDaoApi;
import com.khodko.RoyalHotel.dao.mapper.UserMapper;
import com.khodko.RoyalHotel.dao.mapper.UserRoleJoinMapper;
import com.khodko.RoyalHotel.dao.querydsl.Query;
import com.khodko.RoyalHotel.dao.querydsl.Select;
import com.khodko.RoyalHotel.dao.schema.RoleTable;
import com.khodko.RoyalHotel.dao.schema.UserRoleTable;
import com.khodko.RoyalHotel.dao.schema.UserTable;

import static com.khodko.RoyalHotel.dao.querydsl.where.Where.*;
import static com.khodko.RoyalHotel.dao.schema.UserTable.*;
import com.khodko.RoyalHotel.dao.core.QueryDslDao;
import com.khodko.RoyalHotel.model.User;


public class UserDao extends QueryDslDao<User> implements UserDaoApi {
	
	private final RoleTable roleTable = RoleTable.INSTANCE;
	private final UserRoleTable userRoleTable = UserRoleTable.INSTANCE;
    
    public UserDao(ProxyConnection connection) {
        super(connection, UserTable.INSTANCE, new UserMapper());
    }
  
    @Override
    public Optional<User> findByIdJoin(int userId) throws SQLException {
    	Query query = new Select(table, roleTable)
    			.leftJoin(userRoleTable, ID, UserRoleTable.USER_ID)
    			.leftJoin(roleTable, RoleTable.ID, UserRoleTable.ROLE_ID)
    			.where(eq(ID, userId));
    	return executeSingleResultQuery(query, new UserRoleJoinMapper());    	
    }
       
    @Override
	public Optional<User> findByEmail(String email) throws SQLException {
    	Query query = new Select(table).where(eq(EMAIL, email));
    	return executeSingleResultQuery(query);   	
	}
        	       		      
}

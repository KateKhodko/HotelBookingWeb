package com.khodko.RoyalHotel.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.khodko.RoyalHotel.connection.ProxyConnection;
import com.khodko.RoyalHotel.dao.RememberedLoginDaoApi;
import com.khodko.RoyalHotel.dao.mapper.RememberedLoginMapper;
import com.khodko.RoyalHotel.dao.querydsl.Delete;
import com.khodko.RoyalHotel.dao.querydsl.Query;
import com.khodko.RoyalHotel.dao.querydsl.Select;
import com.khodko.RoyalHotel.dao.querydsl.Update;
import com.khodko.RoyalHotel.dao.schema.RememberedLoginTable;

import static com.khodko.RoyalHotel.dao.schema.RememberedLoginTable.*;
import com.khodko.RoyalHotel.model.RememberedLogin;
import com.khodko.RoyalHotel.dao.core.QueryDslDao;
import static com.khodko.RoyalHotel.dao.querydsl.where.Where.*;


public class RememberedLoginDao extends QueryDslDao<RememberedLogin> implements RememberedLoginDaoApi {
	    
    public RememberedLoginDao(ProxyConnection connection) {
        super(connection, RememberedLoginTable.INSTANCE, new RememberedLoginMapper());                        
    }
        
    @Override
	public Optional<RememberedLogin> findByToken(String token) throws SQLException {
    	Query query = new Select(table).where(eq(TOKEN_HASH, token));
		return executeSingleResultQuery(query);		
	}
	
	@Override
	public Optional<RememberedLogin> findByUserId(int userId) throws SQLException {
		Query query = new Select(table).where(eq(USER_ID, userId));
		return executeSingleResultQuery(query);			
	}
		
	@Override
	public void deleteByUserId(int userId) throws SQLException {
		Query query = new Delete(table).where(eq(USER_ID, userId));
		delete(query);		
	}
			
	@Override
    public RememberedLogin update(RememberedLogin entity) throws SQLException {
		Map<String, Object> map = new HashMap<>();
    	map.put(TOKEN_HASH, entity.getTokenHash());
    	map.put(EXPIRES_AT, entity.getExpiereAt());  
		Query query = new Update(table, map).where(eq(USER_ID, entity.getUserId()));		
    	update(query);
    	return entity;
    }
			      
}

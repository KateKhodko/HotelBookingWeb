package com.khodko.RoyalHotel.dao;

import java.sql.SQLException;
import java.util.Optional;

import com.khodko.RoyalHotel.dao.core.Dao;
import com.khodko.RoyalHotel.model.RememberedLogin;


public interface RememberedLoginDaoApi extends Dao<RememberedLogin> {
	
	Optional<RememberedLogin> findByToken(String token) throws SQLException;
	
	Optional<RememberedLogin> findByUserId(int userId) throws SQLException;
			
	void deleteByUserId(int userId) throws SQLException;
   
}

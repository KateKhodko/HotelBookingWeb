package com.khodko.RoyalHotel.dao;

import java.sql.SQLException;
import java.util.Optional;

import com.khodko.RoyalHotel.dao.core.Dao;
import com.khodko.RoyalHotel.model.User;


public interface UserDaoApi extends Dao<User> {

    Optional<User> findByEmail(String email) throws SQLException;

	Optional<User> findByIdJoin(int userId) throws SQLException;
   
}

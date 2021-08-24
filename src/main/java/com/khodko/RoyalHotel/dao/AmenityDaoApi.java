package com.khodko.RoyalHotel.dao;

import com.khodko.RoyalHotel.dao.core.Dao;
import com.khodko.RoyalHotel.model.Amenity;

import java.sql.SQLException;
import java.util.List;


public interface AmenityDaoApi extends Dao<Amenity> {
   
    List<Amenity> findByRoomType(int typeRoomId) throws SQLException;
   
	List<Amenity> findAll(int i18nId) throws SQLException;

	List<Amenity> findAllByRoomType(int typeRoomId, int i18nId) throws SQLException;
}

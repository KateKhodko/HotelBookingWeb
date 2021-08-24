package com.khodko.RoyalHotel.dao;

import java.sql.SQLException;

import com.khodko.RoyalHotel.dao.core.Dao;
import com.khodko.RoyalHotel.model.AmenityRoomType;


public interface AmenityRoomTypeDaoApi extends Dao<AmenityRoomType> {

    boolean existsAmenity(int id) throws SQLException;
    
    void deleteByRoomType(int roomTypeId) throws SQLException;
}

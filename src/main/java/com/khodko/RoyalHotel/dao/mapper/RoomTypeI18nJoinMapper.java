package com.khodko.RoyalHotel.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.khodko.RoyalHotel.dao.core.Mapper;
import com.khodko.RoyalHotel.model.RoomType;
import com.khodko.RoyalHotel.model.RoomTypeI18n;

public class RoomTypeI18nJoinMapper implements Mapper<RoomType> {

	@Override
    public RoomType map(ResultSet resultSet) throws SQLException {
        RoomType roomType = new RoomTypeMapper().map(resultSet);
        RoomTypeI18n roomTypeI18n = new RoomTypeI18nMapper().map(resultSet);
        if (roomTypeI18n.getId() > 0) {
        	roomType.setRoomTypeI18n(roomTypeI18n);
        }             
        return roomType;
    }

}

package com.khodko.RoyalHotel.dao.mapper;

import com.khodko.RoyalHotel.dao.core.Mapper;
import static com.khodko.RoyalHotel.dao.schema.RoomTypeTable.*;
import com.khodko.RoyalHotel.model.RoomType;

import java.sql.ResultSet;
import java.sql.SQLException;


public class RoomTypeMapper implements Mapper<RoomType> {

    @Override
    public RoomType map(ResultSet resultSet) throws SQLException {
        return new RoomType(
                resultSet.getInt(ID),
                resultSet.getInt(OCCUPANCY),
                resultSet.getString(IMAGE),
                resultSet.getInt(SIZE),
                resultSet.getInt(PRICE),
                resultSet.getInt(ROOMS),
                resultSet.getBoolean(ACCESS));
    }
}

package com.khodko.RoyalHotel.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.khodko.RoyalHotel.dao.core.Mapper;
import static com.khodko.RoyalHotel.dao.schema.AmenityRoomTypeTable.*;
import com.khodko.RoyalHotel.model.AmenityRoomType;


public class AmenityRoomTypeMapper implements Mapper<AmenityRoomType> {

	@Override
	public AmenityRoomType map(ResultSet resultSet) throws SQLException {
		return new AmenityRoomType(
				resultSet.getInt(ROOM_TYPE_ID),
                resultSet.getInt(AMENITY_ID));
	}
}

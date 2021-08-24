package com.khodko.RoyalHotel.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.khodko.RoyalHotel.dao.core.Mapper;
import com.khodko.RoyalHotel.model.RoomTypeI18n;
import static com.khodko.RoyalHotel.dao.schema.RoomTypeI18nTable.*;


public class RoomTypeI18nMapper implements Mapper<RoomTypeI18n> {

	@Override
	public RoomTypeI18n map(ResultSet resultSet) throws SQLException {
		return new RoomTypeI18n(
    		resultSet.getInt(ID),
    		resultSet.getInt(ROOM_TYPE_ID),
    		resultSet.getInt(I18N_ID),   		
            resultSet.getString(NAME),
            resultSet.getString(DESCRIPTION));
	}
}

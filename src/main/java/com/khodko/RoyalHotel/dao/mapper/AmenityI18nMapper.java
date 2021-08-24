package com.khodko.RoyalHotel.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.khodko.RoyalHotel.dao.core.Mapper;
import static com.khodko.RoyalHotel.dao.schema.AmenityI18nTable.*;
import com.khodko.RoyalHotel.model.AmenityI18n;


public class AmenityI18nMapper implements Mapper<AmenityI18n> {

	@Override
	public AmenityI18n map(ResultSet resultSet) throws SQLException {
		return new AmenityI18n(
    		resultSet.getInt(ID),
    		resultSet.getInt(AMENITY_ID),
    		resultSet.getInt(I18N_ID),
            resultSet.getString(NAME));
	}
}

package com.khodko.RoyalHotel.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.khodko.RoyalHotel.dao.core.Mapper;
import static com.khodko.RoyalHotel.dao.schema.AmenityTable.*;
import com.khodko.RoyalHotel.model.Amenity;


public class AmenityMapper implements Mapper<Amenity> {

	@Override
	public Amenity map(ResultSet resultSet) throws SQLException {
		return new Amenity(
    		resultSet.getInt(ID),
            resultSet.getInt(PRICE));
	}
}

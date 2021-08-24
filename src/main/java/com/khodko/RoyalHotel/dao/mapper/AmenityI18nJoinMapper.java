package com.khodko.RoyalHotel.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.khodko.RoyalHotel.dao.core.Mapper;
import com.khodko.RoyalHotel.model.Amenity;
import com.khodko.RoyalHotel.model.AmenityI18n;


public class AmenityI18nJoinMapper implements Mapper<Amenity> {

	@Override
	public Amenity map(ResultSet resultSet) throws SQLException {
		Amenity amenity = new AmenityMapper().map(resultSet);		
		AmenityI18n amenityI18n = new AmenityI18nMapper().map(resultSet);
    	if (amenityI18n.getId() > 0) {
    		amenity.setAmenityI18n(amenityI18n);
    	}		
		return amenity;
	}
}

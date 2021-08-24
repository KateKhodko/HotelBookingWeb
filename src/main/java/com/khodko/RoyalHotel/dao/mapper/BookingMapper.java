package com.khodko.RoyalHotel.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.khodko.RoyalHotel.dao.core.Mapper;
import static com.khodko.RoyalHotel.dao.schema.BookingTable.*;
import com.khodko.RoyalHotel.model.Booking;


public class BookingMapper implements Mapper<Booking> {
	
	@Override
	public Booking map(ResultSet resultSet) throws SQLException {
		return new Booking(
    		resultSet.getInt(ID),
            resultSet.getInt(ROOM_TYPE_ID),
            resultSet.getInt(USER_ID),
            resultSet.getDate(ARRIVAL_DATE),
            resultSet.getDate(DEPART_DATE),
            resultSet.getInt(NUM_ADULTS),
            resultSet.getInt(NUM_CHILDREN),
			resultSet.getInt(PRICE));
	}
}

package com.khodko.RoyalHotel.dao.mapper;

import com.khodko.RoyalHotel.dao.core.Mapper;
import com.khodko.RoyalHotel.model.Booking;
import com.khodko.RoyalHotel.model.RoomType;
import com.khodko.RoyalHotel.model.RoomTypeI18n;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BookingRoomTypeI18nJoinMapper implements Mapper<Booking> {
	
	@Override
	public Booking map(ResultSet resultSet) throws SQLException {
		Booking booking = new BookingMapper().map(resultSet);
		RoomType roomType = new RoomTypeMapper().map(resultSet);
		RoomTypeI18n roomTypeI18n = new RoomTypeI18nMapper().map(resultSet);
        if (roomTypeI18n.getId() > 0) {
        	roomType.setRoomTypeI18n(roomTypeI18n);
        }		
		booking.setRoomType(roomType);		
		return booking;
	}
}

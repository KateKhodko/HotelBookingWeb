package com.khodko.RoyalHotel.dao.schema;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import com.khodko.RoyalHotel.model.Booking;

public class BookingTable implements Table<Booking> {
	
	public static final BookingTable INSTANCE = new BookingTable(); 
	
	public static final String TABLE = "booking";
    
    public static final String ID = TABLE + ".id";
    public static final String ROOM_TYPE_ID = TABLE + ".room_type_id";
    public static final String USER_ID = TABLE + ".user_id";
    public static final String ARRIVAL_DATE = TABLE + ".arrival_date";
    public static final String DEPART_DATE = TABLE + ".depart_date";
    public static final String NUM_ADULTS = TABLE + ".num_adults";
    public static final String NUM_CHILDREN = TABLE + ".num_children";
    public static final String PRICE = TABLE + ".price";
    
    private static final String[] COLUMNS = new String[] {ID, ROOM_TYPE_ID, USER_ID, ARRIVAL_DATE, DEPART_DATE, NUM_ADULTS, NUM_CHILDREN, PRICE};

    private BookingTable() {
    	
    }
    
	@Override
	public String getName() {
		return TABLE;
	}

	@Override
	public String[] getColumns() {
		return COLUMNS;
	}
	
	@Override
	public Map<String, Object> getSaveMap(Booking entity) {
		Map<String, Object> map = new HashMap<>();
    	map.put(ROOM_TYPE_ID, entity.getRoomTypeId());
    	map.put(USER_ID, entity.getUserId());
    	map.put(ARRIVAL_DATE, new Date(entity.getArrivalDate().getTime()));
    	map.put(DEPART_DATE, new Date(entity.getDepartDate().getTime()));
    	map.put(NUM_ADULTS, entity.getNumAdults());
    	map.put(NUM_CHILDREN, entity.getNumChildren());
    	map.put(PRICE, entity.getPrice());
    	return map;
	}

}

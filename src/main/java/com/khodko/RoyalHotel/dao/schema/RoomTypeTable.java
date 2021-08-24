package com.khodko.RoyalHotel.dao.schema;

import java.util.HashMap;
import java.util.Map;

import com.khodko.RoyalHotel.model.RoomType;

public class RoomTypeTable implements Table<RoomType> {
	
	public static final RoomTypeTable INSTANCE = new RoomTypeTable();
	
	public static final String TABLE = "room_type";

    public static final String ID = TABLE + ".id";
    public static final String OCCUPANCY = TABLE + ".occupancy";
    public static final String IMAGE = TABLE + ".image";
    public static final String SIZE = TABLE + ".size";
    public static final String PRICE = TABLE + ".price";
    public static final String ROOMS = TABLE + ".rooms";
    public static final String ACCESS = TABLE + ".access";
    
    private static final String[] COLUMNS = new String[] {ID, OCCUPANCY, IMAGE, SIZE, PRICE, ROOMS, ACCESS};
    
    private RoomTypeTable() {
    	
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
	public Map<String, Object> getSaveMap(RoomType entity) {
		Map<String, Object> map = new HashMap<>();
    	map.put(OCCUPANCY, entity.getOccupancy());
    	map.put(IMAGE, entity.getImage());
    	map.put(SIZE, entity.getSize());
    	map.put(PRICE, entity.getPrice());
    	map.put(ROOMS, entity.getRooms());
    	map.put(ACCESS, entity.isAccess());
		return map;
	}

}

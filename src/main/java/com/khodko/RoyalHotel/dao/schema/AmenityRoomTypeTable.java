package com.khodko.RoyalHotel.dao.schema;

import java.util.HashMap;
import java.util.Map;

import com.khodko.RoyalHotel.model.AmenityRoomType;

public class AmenityRoomTypeTable implements Table<AmenityRoomType> {
	
	public static final AmenityRoomTypeTable INSTANCE = new AmenityRoomTypeTable();
	
	public static final String TABLE = "amenity_room_type";
	
	public static final String ROOM_TYPE_ID = TABLE + ".room_type_id";
	public static final String AMENITY_ID = TABLE + ".amenity_id";
	
	private static final String[] COLUMNS = new String[] {ROOM_TYPE_ID, AMENITY_ID};
	
	private AmenityRoomTypeTable() {
		
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
	public Map<String, Object> getSaveMap(AmenityRoomType entity) {
    	Map<String, Object> map = new HashMap<>();
    	map.put(ROOM_TYPE_ID, entity.getRoomTypeId());
    	map.put(AMENITY_ID, entity.getAmenityId());
    	return map;
	}

}

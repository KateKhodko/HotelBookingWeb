package com.khodko.RoyalHotel.dao.schema;

import java.util.HashMap;
import java.util.Map;

import com.khodko.RoyalHotel.model.Amenity;

public class AmenityTable implements Table<Amenity> {
	
	public static final AmenityTable INSTANCE = new AmenityTable();
	
	public static final String TABLE = "amenity";
    
    public static final String ID = TABLE + ".id";
    public static final String PRICE = TABLE + ".price";
    
    private static final String[] COLUMNS = new String[] {ID, PRICE};
    
    private AmenityTable() {
    	
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
	public Map<String, Object> getSaveMap(Amenity amenity) {
    	Map<String, Object> map = new HashMap<>();
    	map.put(PRICE, amenity.getPrice());
    	return map;
    }
		
}

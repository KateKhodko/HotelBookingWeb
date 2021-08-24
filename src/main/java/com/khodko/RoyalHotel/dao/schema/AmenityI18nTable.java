package com.khodko.RoyalHotel.dao.schema;

import java.util.HashMap;
import java.util.Map;

import com.khodko.RoyalHotel.model.AmenityI18n;

public class AmenityI18nTable implements Table<AmenityI18n> {
	
	public static final AmenityI18nTable INSTANCE = new AmenityI18nTable();
	
	public static final String TABLE = "amenity_i18n";
    
    public static final String ID = TABLE + ".id";
    public static final String AMENITY_ID = TABLE + ".amenity_id";
    public static final String I18N_ID = TABLE + ".i18n_id";   
    public static final String NAME = TABLE + ".name";
    
    private static final String[] COLUMNS = new String[] {ID, AMENITY_ID, I18N_ID, NAME};
    
    private AmenityI18nTable() {
    	
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
	public Map<String, Object> getSaveMap(AmenityI18n obj) {
    	Map<String, Object> map = new HashMap<>();
    	map.put(AMENITY_ID, obj.getAmenityId());
    	map.put(I18N_ID, obj.getI18nId());
    	map.put(NAME, obj.getName());
    	return map;
    }

}

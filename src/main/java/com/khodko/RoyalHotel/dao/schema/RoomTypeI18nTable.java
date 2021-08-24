package com.khodko.RoyalHotel.dao.schema;

import java.util.HashMap;
import java.util.Map;

import com.khodko.RoyalHotel.model.RoomTypeI18n;

public class RoomTypeI18nTable implements Table<RoomTypeI18n> {
	
	public static final RoomTypeI18nTable INSTANCE = new RoomTypeI18nTable();
	
	public static final String TABLE = "room_type_i18n";
    
    public static final String ID = TABLE + ".id";
    public static final String ROOM_TYPE_ID = TABLE + ".room_type_id";
    public static final String I18N_ID = TABLE + ".i18n_id";   
    public static final String NAME = TABLE + ".name";
    public static final String DESCRIPTION = TABLE + ".description";
    
    private static final String[] COLUMNS = new String[] {ID, ROOM_TYPE_ID, I18N_ID, NAME, DESCRIPTION};
    
    private RoomTypeI18nTable() {
    	
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
	public Map<String, Object> getSaveMap(RoomTypeI18n entity) {
		Map<String, Object> map = new HashMap<>();
    	map.put(ROOM_TYPE_ID, entity.getRoomTypeId());
    	map.put(I18N_ID, entity.getI18nId());
    	map.put(NAME, entity.getName());
    	map.put(DESCRIPTION, entity.getDescription());
		return map;
	}

}

package com.khodko.RoyalHotel.dao.schema;

import java.util.HashMap;
import java.util.Map;

import com.khodko.RoyalHotel.model.I18n;

public class I18nTable implements Table<I18n> {
	
	public static final I18nTable INSTANCE = new I18nTable(); 
	
	public static final String TABLE = "i18n";
    
    public static final String ID = TABLE + ".id";
    public static final String LOCALE = TABLE + ".locale";
    public static final String NAME = TABLE + ".name";
    
    private static final String[] COLUMNS = new String[] {ID, LOCALE, NAME};
    
    private I18nTable() {
    	
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
	public Map<String, Object> getSaveMap(I18n entity) {
		Map<String, Object> map = new HashMap<>();
    	map.put(LOCALE, entity.getLocale());
    	map.put(NAME, entity.getName());
		return map;
	}

}

package com.khodko.RoyalHotel.dao.schema;

import java.util.HashMap;
import java.util.Map;

import com.khodko.RoyalHotel.model.User;

public class UserTable implements Table<User> {
	
	public static final UserTable INSTANCE = new UserTable();
	
	public static final String TABLE = "user";
    
    public static final String ID = TABLE + ".id";
    public static final String USERNAME = TABLE + ".username";
    public static final String PASSWORD = TABLE + ".password";
    public static final String EMAIL = TABLE + ".email";
    public static final String FIRST_NAME = TABLE + ".first_name";
    public static final String LAST_NAME = TABLE + ".last_name";
    public static final String COUNTRY = TABLE + ".country";
    public static final String CARD_TYPE = TABLE + ".card_type";
    public static final String CARD_NUMBER = TABLE + ".card_number";
    
    private static final String[] COLUMNS = new String[] {ID, USERNAME, PASSWORD, EMAIL, FIRST_NAME, LAST_NAME, COUNTRY, CARD_TYPE, CARD_NUMBER};

    private UserTable() {
    	
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
	public Map<String, Object> getSaveMap(User entity) {
		Map<String, Object> map = new HashMap<>();
		map.put(USERNAME, entity.getUsername());
		map.put(PASSWORD, entity.getPassword());
		map.put(EMAIL, entity.getEmail());
		map.put(FIRST_NAME, entity.getFirstName());
		map.put(LAST_NAME, entity.getLastName());
		map.put(COUNTRY, entity.getCountry());
		map.put(CARD_TYPE, entity.getCardType());
		map.put(CARD_NUMBER, entity.getCardNumber());
		return map;
	}
	
}

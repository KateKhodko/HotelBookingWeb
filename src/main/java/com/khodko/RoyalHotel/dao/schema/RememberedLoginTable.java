package com.khodko.RoyalHotel.dao.schema;

import java.util.HashMap;
import java.util.Map;

import com.khodko.RoyalHotel.model.RememberedLogin;

public class RememberedLoginTable implements Table<RememberedLogin> {
	
	public static final RememberedLoginTable INSTANCE = new RememberedLoginTable();
	
	public static final String TABLE = "remembered_login";
    
    public static final String TOKEN_HASH = TABLE + ".token_hash";
    public static final String USER_ID = TABLE + ".user_id";
    public static final String EXPIRES_AT = TABLE + ".expires_at";
    
    private static final String[] COLUMNS = new String[] {TOKEN_HASH, USER_ID, EXPIRES_AT};
    
    private RememberedLoginTable() {
    	
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
	public Map<String, Object> getSaveMap(RememberedLogin entity) {
		Map<String, Object> map = new HashMap<>();
    	map.put(TOKEN_HASH, entity.getTokenHash());
    	map.put(USER_ID, entity.getUserId());
    	map.put(EXPIRES_AT, entity.getExpiereAt());    	
		return map;
	}

}

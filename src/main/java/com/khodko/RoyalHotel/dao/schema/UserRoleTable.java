package com.khodko.RoyalHotel.dao.schema;

import java.util.HashMap;
import java.util.Map;

import com.khodko.RoyalHotel.model.UserRole;

public class UserRoleTable implements Table<UserRole> {
	
	public static final UserRoleTable INSTANCE = new UserRoleTable();
	
	public static final String TABLE = "user_role";
    
    public static final String USER_ID = TABLE + ".user_id";
    public static final String ROLE_ID = TABLE + ".role_id";
        
    private static final String[] COLUMNS = new String[] {USER_ID, ROLE_ID};
    
    private UserRoleTable() {
    
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
	public Map<String, Object> getSaveMap(UserRole entity) {
		Map<String, Object> map = new HashMap<>();
    	map.put(USER_ID, entity.getUserId());
    	map.put(ROLE_ID, entity.getRoleId());
		return map;
	}

}

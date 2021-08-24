package com.khodko.RoyalHotel.dao.schema;

import java.util.HashMap;
import java.util.Map;

import com.khodko.RoyalHotel.model.Role;

public class RoleTable implements Table<Role> {
	
	public static final RoleTable INSTANCE = new RoleTable();
	
	public static final String TABLE = "role";
    
    public static final String ID = TABLE + ".id";
    public static final String ROLE = TABLE + ".role";
    
    private static final String[] COLUMNS = new String[] {ID, ROLE};
    
    private RoleTable() {
    	
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
	public Map<String, Object> getSaveMap(Role entity) {
		Map<String, Object> map = new HashMap<>();
    	map.put(ROLE, entity.getRole());
		return map;
	}

}

package com.khodko.RoyalHotel.dao.schema;

import java.util.Map;

import com.khodko.RoyalHotel.model.DbEntity;

public interface Table<T extends DbEntity> {
	
	String getName();
	
	String[] getColumns();
	
	Map<String, Object> getSaveMap(T entity);

}

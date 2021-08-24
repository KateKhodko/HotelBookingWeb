package com.khodko.RoyalHotel.dao.querydsl;

import java.util.Map;

import com.khodko.RoyalHotel.dao.schema.Table;

/**
 * INSERT INTO %s (%s, %s) VALUES (?, ?)
 */
public class Insert extends Query {
	
	public Insert(Table<?> table, Map<String, Object> valueMap) {
		super();
		insert(table, valueMap);		
	}
		
	private Insert insert(Table<?> table, Map<String, Object> valueMap) {
		queryBuilder.append("INSERT INTO ").append(table.getName()).append(" (");
		
		StringBuilder columnssb = new StringBuilder("");
		StringBuilder valuessb = new StringBuilder("");
		for (Map.Entry<String, Object> entry : valueMap.entrySet()) {
			columnssb.append(entry.getKey()).append(",");
			valuessb.append("?,");
			parameters.add(entry.getValue());
		}
		int lastIndex = columnssb.lastIndexOf(",");
		if (lastIndex != -1) {
			columnssb.deleteCharAt(lastIndex);
		}
		lastIndex = valuessb.lastIndexOf(",");
		if (lastIndex != -1) {
			valuessb.deleteCharAt(lastIndex);
		}
			
		queryBuilder.append(columnssb).append(") VALUES (").append(valuessb).append(")");		
		return this;
	}

}

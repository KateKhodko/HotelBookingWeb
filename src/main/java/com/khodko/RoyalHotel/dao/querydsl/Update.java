package com.khodko.RoyalHotel.dao.querydsl;

import java.util.Map;

import com.khodko.RoyalHotel.dao.schema.Table;

/**
 * UPDATE %s SET %s = ?, %s = ? WHERE %s = ?
 */
public class Update extends WhereQuery {
		
	public Update(Table<?> table, Map<String, Object> valueMap) {
		super();
		update(table, valueMap);
	}
	
	private Update update(Table<?> table, Map<String, Object> valueMap) {		
		queryBuilder.append("UPDATE ").append(table.getName()).append(" SET ");
		
		StringBuilder sb = new StringBuilder("");
		for (Map.Entry<String, Object> entry : valueMap.entrySet()) {
			sb.append(entry.getKey()).append(" = ?,");
			parameters.add(entry.getValue());
		}
		int lastIndex = sb.lastIndexOf(",");
		if (lastIndex != -1) {
			sb.deleteCharAt(lastIndex);
		}	
		queryBuilder.append(sb);
		return this;
	}

}

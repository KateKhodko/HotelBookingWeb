package com.khodko.RoyalHotel.dao.querydsl;

import com.khodko.RoyalHotel.dao.schema.Table;

/**
 * DELETE FROM %s WHERE id = ?
 */
public class Delete extends WhereQuery {
	
	public Delete(Table<?> table) {
		super();
		delete(table);
	}		
	
	private Delete delete(Table<?> table) {
		queryBuilder.append(" DELETE FROM ").append(table.getName());
		return this;
	}

}

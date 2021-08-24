package com.khodko.RoyalHotel.dao.querydsl;

import com.khodko.RoyalHotel.dao.querydsl.where.BaseWhere;
import com.khodko.RoyalHotel.dao.schema.Table;

/**
 * SELECT %s, %s FROM %s JOIN %s ON %s = %s WHERE %s = ?
 */
public class Select extends WhereQuery {
		
	public Select(String columns, Table<?> table) {
		super();
		select(columns, table);	
	}
	
	public Select(Table<?>... tables) {
		super();
		select(tables);	
	}
			
	public Select where(BaseWhere... where) {
		return (Select) super.where(where);
	}
					
	private Select select(Table<?>... tables) {		
		queryBuilder.append("SELECT ");
		for (Table<?> table : tables) {
			String columnsStr = String.join(",", table.getColumns());
			queryBuilder.append(columnsStr).append(",");
		}
		int lastIndex = queryBuilder.lastIndexOf(",");
		if (lastIndex != -1) {
			queryBuilder.deleteCharAt(lastIndex);
		}
		queryBuilder.append(" FROM ").append(tables[0].getName());		
		return this;
	}
	
	private Select select(String columns, Table<?> table) {		
		queryBuilder.append("SELECT ").append(columns).append(" FROM ").append(table.getName());					
		return this;
	}
	
	public Select join(Table<?> table, String refKey, String onKey) {		
		return join(" INNER", table, refKey, onKey);
	}
	
	public Select outerJoin(Table<?> table, String refKey, String onKey) {		
		return join(" OUTER", table, refKey, onKey);
	}
	
	public Select leftJoin(Table<?> table, String refKey, String onKey) {		
		return join(" LEFT", table, refKey, onKey);
	}
	
	public Select rightJoin(Table<?> table, String refKey, String onKey) {		
		return join(" RIGHT", table, refKey, onKey);
	}
	
	private Select join(String joinType, Table<?> table, String refKey, String onKey) {
		queryBuilder.append(joinType).append(" JOIN ").append(table.getName()).append(" ON ").append(refKey).append(" = ").append(onKey);
		return this;
	}
	
	public Select limit(Object limit, Object offset) {
		parameters.add(limit);
		parameters.add(offset);
		queryBuilder.append(" LIMIT ? OFFSET ?");
		return this;
	}
	
	public Select orderBy(String... columns) {
		return orderBy(true, columns);
	}
	
	public Select orderBy(boolean asc, String... columns) {
		String columnsStr = String.join(" ,", columns);
		String order = asc ? " ASC" : " DESC";
		queryBuilder.append(" ORDER BY ").append(columnsStr).append(order);
		return this;
	}	
	
	public Select orderBy(OrderBy orderBy) {
		queryBuilder.append(orderBy.getQuery());
		return this;
	}

}

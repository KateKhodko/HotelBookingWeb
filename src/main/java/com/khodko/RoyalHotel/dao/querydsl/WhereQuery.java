package com.khodko.RoyalHotel.dao.querydsl;

import com.khodko.RoyalHotel.dao.querydsl.where.BaseWhere;

public abstract class WhereQuery extends Query {
	
	private boolean existsWhere = false;
	
	public WhereQuery where(BaseWhere... where) {
		if (where == null || where.length == 0 || where[0] == null) return this;
		if (!existsWhere) {
			queryBuilder.append(" WHERE ");
			existsWhere = true;
		} else {
			queryBuilder.append(" AND ");
		}				
		String and = " AND ";
		for (BaseWhere w : where) {
			if (w == null) continue;
			parameters.addAll(w.getParameters());	
			queryBuilder.append(w.getQuery()).append(and);
		}
		int lastIndex = queryBuilder.lastIndexOf(and);
		if (lastIndex != -1) {
			queryBuilder.delete(lastIndex, queryBuilder.length());
		}				
		return this;
	}

}

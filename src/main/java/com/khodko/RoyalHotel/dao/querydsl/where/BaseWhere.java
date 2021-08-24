package com.khodko.RoyalHotel.dao.querydsl.where;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseWhere {
		
	protected StringBuilder queryBuilder = new StringBuilder("");
	protected List<Object> parameters = new ArrayList<>();
	
	public BaseWhere() {
		
	}
	
	public BaseWhere and(BaseWhere where) {
		return add("AND", where);
	}
	
	public BaseWhere or(BaseWhere where) {
		return add("OR", where);
	}
	
	private BaseWhere add(String clause, BaseWhere where) {
		if (where != null) {
			queryBuilder.append(" ").append(clause).append(" ").append(where.getQuery());
			parameters.addAll(where.getParameters());
		}
		return this;
	}
	
	public String getQuery() {
		return queryBuilder.toString();
	}
		
	public List<Object> getParameters() {
		return parameters;
	}
	
}

package com.khodko.RoyalHotel.dao.querydsl;

import java.util.ArrayList;
import java.util.List;

public abstract class Query {
	
	protected StringBuilder queryBuilder = new StringBuilder("");
	protected List<Object> parameters = new ArrayList<>();
	
	public Query() {
		
	}
	
	public String getQuery() {
		return queryBuilder.toString();
	}
		
	public Object[] getParameters() {
		Object[] array = new Object[parameters.size()];
		return parameters.toArray(array);
	}

}

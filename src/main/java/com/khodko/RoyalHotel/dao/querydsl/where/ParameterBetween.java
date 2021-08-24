package com.khodko.RoyalHotel.dao.querydsl.where;

class ParameterBetween extends BaseWhere {
	
	ParameterBetween(Object parameter, String column1, String column2) {
		parameters.add(parameter);	
		queryBuilder.append(" ? BETWEEN ").append(column1).append(" AND ").append(column2);
	}

}

package com.khodko.RoyalHotel.dao.querydsl.where;

class NotParameterBetween extends BaseWhere {
	
	NotParameterBetween(Object parameter, String column1, String column2) {
		parameters.add(parameter);	
		queryBuilder.append(" ? NOT BETWEEN ").append(column1).append(" AND ").append(column2);
	}

}

package com.khodko.RoyalHotel.dao.querydsl.where;

class NotBetween extends BaseWhere {
	
	NotBetween(String column, Object parameter1, Object parameter2) {
		parameters.add(parameter1);	
		parameters.add(parameter2);
		queryBuilder.append(" ").append(column).append(" NOT BETWEEN ? AND ?");
	}

}

package com.khodko.RoyalHotel.dao.querydsl.where;

class Between extends BaseWhere {
	
	Between(String column, Object parameter1, Object parameter2) {
		parameters.add(parameter1);	
		parameters.add(parameter2);
		queryBuilder.append(" ").append(column).append(" BETWEEN ? AND ?");
	}
		
}

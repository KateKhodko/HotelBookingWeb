package com.khodko.RoyalHotel.dao.querydsl.where;

class IsNotNull extends BaseWhere {

	IsNotNull(String column) {	
		queryBuilder.append(" ").append(column).append(" IS NOT NULL");						
	}
	
}

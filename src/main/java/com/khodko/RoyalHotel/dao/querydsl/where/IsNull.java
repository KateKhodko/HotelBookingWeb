package com.khodko.RoyalHotel.dao.querydsl.where;

class IsNull extends BaseWhere {

	IsNull(String column) {	
		queryBuilder.append(" ").append(column).append(" IS NULL");						
	}
	
}

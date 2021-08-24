package com.khodko.RoyalHotel.dao.querydsl.where;

class EqualColumns extends BaseWhere {

	EqualColumns(String column1, String column2) {		
		queryBuilder.append(" ").append(column1).append(" = ").append(column2);
	}						
	
}

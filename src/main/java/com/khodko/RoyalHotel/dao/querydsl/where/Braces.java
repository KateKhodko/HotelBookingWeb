package com.khodko.RoyalHotel.dao.querydsl.where;


class Braces extends BaseWhere {

	Braces(BaseWhere where) {
		queryBuilder.append(" ( ").append(where.getQuery()).append(" ) ");
		parameters.addAll(where.getParameters());					
	}
	
}

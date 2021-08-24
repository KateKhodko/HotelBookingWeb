package com.khodko.RoyalHotel.dao.querydsl.where;

class NotEqual extends Compare {
	
	NotEqual(String column, Object parameter) {
		super("!=", column, parameter);
	}

}

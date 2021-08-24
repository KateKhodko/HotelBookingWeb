package com.khodko.RoyalHotel.dao.querydsl.where;

class Equal extends Compare {
	
	Equal(String column, Object parameter) {
		super("=", column, parameter);
	}

}

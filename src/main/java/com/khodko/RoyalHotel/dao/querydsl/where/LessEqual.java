package com.khodko.RoyalHotel.dao.querydsl.where;

class LessEqual extends Compare {
	
	LessEqual(String column, Object parameter) {
		super("<=", column, parameter);
	}

}

package com.khodko.RoyalHotel.dao.querydsl.where;

class LessThan extends Compare {
	
	LessThan(String column, Object parameter) {
		super("<", column, parameter);
	}

}

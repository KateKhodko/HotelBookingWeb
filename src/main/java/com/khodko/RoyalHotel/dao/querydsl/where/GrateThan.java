package com.khodko.RoyalHotel.dao.querydsl.where;

class GrateThan extends Compare {
	
	GrateThan(String column, Object parameter) {
		super(">", column, parameter);
	}
	
	

}

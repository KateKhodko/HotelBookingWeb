package com.khodko.RoyalHotel.dao.querydsl.where;

class GrateEqual extends Compare {
	
	GrateEqual(String column, Object parameter) {
		super(">=", column, parameter);
	}

}

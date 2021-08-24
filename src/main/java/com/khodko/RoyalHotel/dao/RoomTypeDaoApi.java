package com.khodko.RoyalHotel.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.khodko.RoyalHotel.dao.core.Dao;
import com.khodko.RoyalHotel.dao.querydsl.OrderBy;
import com.khodko.RoyalHotel.model.RoomType;


public interface RoomTypeDaoApi extends Dao<RoomType> {
	
	Optional<RoomType> findById(int id, int i18nId) throws SQLException;

	List<RoomType> findAll(int i18nId, Optional<Date> arrivalDateOpt, Optional<Date> departDateOpt, OrderBy orderBy,
			boolean onlyAccesible) throws SQLException;
	
}

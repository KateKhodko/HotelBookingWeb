package com.khodko.RoyalHotel.dao;

import com.khodko.RoyalHotel.dao.core.Dao;
import com.khodko.RoyalHotel.dao.querydsl.OrderBy;
import com.khodko.RoyalHotel.dao.querydsl.where.BaseWhere;
import com.khodko.RoyalHotel.model.Booking;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public interface BookingDaoApi extends Dao<Booking> {

    List<Booking> findByDate(Date arrivalDate, Date departDate) throws SQLException;
   
	List<Booking> findAll(int i18nId, int limit, int offset, BaseWhere where, OrderBy orderBy) throws SQLException;

	List<Booking> findAllByUserId(int i18nId, int userId, int limit, int offset, BaseWhere where, OrderBy orderBy) throws SQLException;

	Optional<Booking> findById(int id, int i18nId) throws SQLException;

	int countAll(BaseWhere where) throws SQLException;
	
	int countByUserId(int id, BaseWhere where) throws SQLException;

	boolean existsRoomType(int roomTypeId) throws SQLException;
}

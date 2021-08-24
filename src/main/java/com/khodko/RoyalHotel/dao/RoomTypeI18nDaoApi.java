package com.khodko.RoyalHotel.dao;

import java.sql.SQLException;
import java.util.Optional;

import com.khodko.RoyalHotel.dao.core.Dao;
import com.khodko.RoyalHotel.model.RoomTypeI18n;


public interface RoomTypeI18nDaoApi extends Dao<RoomTypeI18n> {

	boolean existsByName(RoomTypeI18n roomTypeI18n) throws SQLException;

	Optional<RoomTypeI18n> findByRoomType(int roomTypeId, int i18nId) throws SQLException;
   
}

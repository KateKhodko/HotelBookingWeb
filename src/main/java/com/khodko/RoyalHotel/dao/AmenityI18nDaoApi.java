package com.khodko.RoyalHotel.dao;

import java.sql.SQLException;
import java.util.Optional;

import com.khodko.RoyalHotel.dao.core.Dao;
import com.khodko.RoyalHotel.model.AmenityI18n;


public interface AmenityI18nDaoApi extends Dao<AmenityI18n> {

	Optional<AmenityI18n> findByAmenity(int amenityId, int i18nId) throws SQLException;

	boolean existsByName(AmenityI18n amenityI18n) throws SQLException;
   
}

package com.khodko.RoyalHotel.dao;

import java.sql.SQLException;

import com.khodko.RoyalHotel.dao.core.Dao;
import com.khodko.RoyalHotel.model.I18n;


public interface I18nDaoApi extends Dao<I18n> {

	boolean existsByLocale(I18n i18n) throws SQLException;
   
}

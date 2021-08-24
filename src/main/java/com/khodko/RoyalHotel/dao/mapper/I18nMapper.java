package com.khodko.RoyalHotel.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.khodko.RoyalHotel.dao.core.Mapper;
import static com.khodko.RoyalHotel.dao.schema.I18nTable.*;
import com.khodko.RoyalHotel.model.I18n;


public class I18nMapper implements Mapper<I18n> {

	@Override
	public I18n map(ResultSet resultSet) throws SQLException {
		return new I18n(
    		resultSet.getInt(ID),
            resultSet.getString(LOCALE),
            resultSet.getString(NAME));
	}
}

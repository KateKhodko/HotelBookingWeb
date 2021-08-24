package com.khodko.RoyalHotel.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.khodko.RoyalHotel.dao.core.Mapper;
import static com.khodko.RoyalHotel.dao.schema.RememberedLoginTable.*;
import com.khodko.RoyalHotel.model.RememberedLogin;


public class RememberedLoginMapper implements Mapper<RememberedLogin> {

	@Override
	public RememberedLogin map(ResultSet resultSet) throws SQLException {
		return new RememberedLogin(
    		resultSet.getString(TOKEN_HASH),
            resultSet.getInt(USER_ID),
            resultSet.getTimestamp(EXPIRES_AT));
	}
}

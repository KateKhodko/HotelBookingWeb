package com.khodko.RoyalHotel.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.khodko.RoyalHotel.dao.core.Mapper;
import com.khodko.RoyalHotel.model.UserRole;
import static com.khodko.RoyalHotel.dao.schema.UserRoleTable.*;


public class UserRoleMapper implements Mapper<UserRole> {

	@Override
	public UserRole map(ResultSet resultSet) throws SQLException {
		return new UserRole(
    		resultSet.getInt(USER_ID),
            resultSet.getInt(ROLE_ID));
	}
}

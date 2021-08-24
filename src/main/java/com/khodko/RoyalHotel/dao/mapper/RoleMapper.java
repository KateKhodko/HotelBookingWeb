package com.khodko.RoyalHotel.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.khodko.RoyalHotel.dao.core.Mapper;
import static com.khodko.RoyalHotel.dao.schema.RoleTable.*;
import com.khodko.RoyalHotel.model.Role;


public class RoleMapper implements Mapper<Role> {

	@Override
	public Role map(ResultSet resultSet) throws SQLException {
		return new Role(
    		resultSet.getInt(ID),
            resultSet.getString(ROLE));
	}
}

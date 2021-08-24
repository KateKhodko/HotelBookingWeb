package com.khodko.RoyalHotel.dao.mapper;

import com.khodko.RoyalHotel.dao.core.Mapper;
import static com.khodko.RoyalHotel.dao.schema.UserTable.*;
import com.khodko.RoyalHotel.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserMapper implements Mapper<User> {

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getInt(ID),
                resultSet.getString(USERNAME),
                resultSet.getString(PASSWORD),
                resultSet.getString(EMAIL),
                resultSet.getString(FIRST_NAME),
                resultSet.getString(LAST_NAME),
                resultSet.getString(COUNTRY),
                resultSet.getString(CARD_TYPE),
                resultSet.getString(CARD_NUMBER));
    }
}

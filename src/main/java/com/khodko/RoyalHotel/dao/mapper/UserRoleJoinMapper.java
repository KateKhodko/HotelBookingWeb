package com.khodko.RoyalHotel.dao.mapper;

import com.khodko.RoyalHotel.dao.core.Mapper;
import com.khodko.RoyalHotel.model.Role;
import com.khodko.RoyalHotel.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRoleJoinMapper implements Mapper<User> {

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        User user = new UserMapper().map(resultSet);
        Role role = new RoleMapper().map(resultSet);
        user.setRole(role.getRole());      
        return user;
    }
}

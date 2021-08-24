package com.khodko.RoyalHotel.dao.impl;

import java.sql.SQLException;

import com.khodko.RoyalHotel.connection.ProxyConnection;
import com.khodko.RoyalHotel.dao.UserRoleDaoApi;
import com.khodko.RoyalHotel.dao.core.BaseDao;
import com.khodko.RoyalHotel.dao.mapper.UserRoleMapper;
import com.khodko.RoyalHotel.dao.schema.UserRoleTable;
import com.khodko.RoyalHotel.model.UserRole;


public class UserRoleDao extends BaseDao<UserRole> implements UserRoleDaoApi {

    public UserRoleDao(ProxyConnection connection) {
        super(connection, UserRoleTable.INSTANCE, new UserRoleMapper());                        
    }
    
    @Override
    public void delete(Integer id) throws SQLException {       
    }
       
}

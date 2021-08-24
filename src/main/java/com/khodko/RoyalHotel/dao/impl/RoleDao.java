package com.khodko.RoyalHotel.dao.impl;

import com.khodko.RoyalHotel.connection.ProxyConnection;
import com.khodko.RoyalHotel.dao.RoleDaoApi;
import com.khodko.RoyalHotel.dao.core.BaseDao;
import com.khodko.RoyalHotel.dao.mapper.RoleMapper;
import com.khodko.RoyalHotel.dao.schema.RoleTable;
import com.khodko.RoyalHotel.model.Role;


public class RoleDao extends BaseDao<Role> implements RoleDaoApi {

    public RoleDao(ProxyConnection connection) {
        super(connection, RoleTable.INSTANCE, new RoleMapper());                        
    }
       
}

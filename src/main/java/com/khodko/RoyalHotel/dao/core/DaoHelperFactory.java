package com.khodko.RoyalHotel.dao.core;

import com.khodko.RoyalHotel.connection.ConnectionPool;
import com.khodko.RoyalHotel.dao.DaoHelper;

public class DaoHelperFactory {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    public DaoHelper createDao(){
        return new DaoHelper(connectionPool.getConnection());
    }

}

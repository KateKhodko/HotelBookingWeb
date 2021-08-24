package com.khodko.RoyalHotel.dao;

import java.sql.SQLException;

import com.khodko.RoyalHotel.connection.ProxyConnection;
import com.khodko.RoyalHotel.dao.impl.AmenityDao;
import com.khodko.RoyalHotel.dao.impl.AmenityI18nDao;
import com.khodko.RoyalHotel.dao.impl.AmenityRoomTypeDao;
import com.khodko.RoyalHotel.dao.impl.BookingDao;
import com.khodko.RoyalHotel.dao.impl.I18nDao;
import com.khodko.RoyalHotel.dao.impl.RememberedLoginDao;
import com.khodko.RoyalHotel.dao.impl.RoleDao;
import com.khodko.RoyalHotel.dao.impl.RoomTypeDao;
import com.khodko.RoyalHotel.dao.impl.RoomTypeI18nDao;
import com.khodko.RoyalHotel.dao.impl.UserDao;


public class DaoHelper implements AutoCloseable {

    private final ProxyConnection proxyConnection;

    public DaoHelper(ProxyConnection proxyConnection) {
        this.proxyConnection = proxyConnection;
    }

    public AmenityDaoApi createAmenityDao() {
        return new AmenityDao(proxyConnection);
    }

    public AmenityRoomTypeDaoApi createAmenityRoomTypeDao() {
        return new AmenityRoomTypeDao(proxyConnection);
    }

    public RoomTypeDaoApi createRoomTypeDao() {
        return new RoomTypeDao(proxyConnection);
    }  
    
    public RoleDaoApi createRoleDao() {
        return new RoleDao(proxyConnection);
    }
    
    public UserDaoApi createUserDao() {
        return new UserDao(proxyConnection);
    }
    
    public RememberedLoginDaoApi createRememberedLoginDao() {
        return new RememberedLoginDao(proxyConnection);
    }
    
    public BookingDaoApi createBookingDao() {
        return new BookingDao(proxyConnection);
    }
    
    public I18nDaoApi createI18nDao() {
        return new I18nDao(proxyConnection);
    }
    
    public AmenityI18nDaoApi createAmenityI18nDao() {
        return new AmenityI18nDao(proxyConnection);
    }
    
    public RoomTypeI18nDaoApi createRoomTypeI18nDao() {
        return new RoomTypeI18nDao(proxyConnection);
    }
    
    @Override
    public void close() throws SQLException {
        proxyConnection.setAutoCommit(true);
        proxyConnection.close();
    }
}

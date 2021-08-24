package com.khodko.RoyalHotel.service;

import java.sql.SQLException;
import java.util.Optional;

import com.khodko.RoyalHotel.dao.DaoHelper;
import com.khodko.RoyalHotel.dao.RoomTypeI18nDaoApi;
import com.khodko.RoyalHotel.dao.core.DaoHelperFactory;
import com.khodko.RoyalHotel.model.RoomTypeI18n;


public class RoomTypeI18nService {
	
	private final DaoHelperFactory daoHelperFactory;

    public RoomTypeI18nService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }
       
    public boolean existsByName(RoomTypeI18n roomTypeI18n) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
        	RoomTypeI18nDaoApi dao = daoHelper.createRoomTypeI18nDao();
            return dao.existsByName(roomTypeI18n);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
    
    public RoomTypeI18n save(RoomTypeI18n roomTypeI18n) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
        	RoomTypeI18nDaoApi dao = daoHelper.createRoomTypeI18nDao();       	
        	return roomTypeI18n.getId() > 0 ? dao.update(roomTypeI18n) : dao.save(roomTypeI18n);               	
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
    
    public  Optional<RoomTypeI18n> findByRoomType(int roomTypeId, int i18nId) throws ServiceException {
    	try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
    		RoomTypeI18nDaoApi dao = daoHelper.createRoomTypeI18nDao(); 
        	return dao.findByRoomType(roomTypeId, i18nId);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
    
}

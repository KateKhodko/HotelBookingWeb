package com.khodko.RoyalHotel.service;

import java.sql.SQLException;
import java.util.List;

import com.khodko.RoyalHotel.dao.AmenityDaoApi;
import com.khodko.RoyalHotel.dao.AmenityRoomTypeDaoApi;
import com.khodko.RoyalHotel.dao.DaoHelper;
import com.khodko.RoyalHotel.dao.core.DaoHelperFactory;
import com.khodko.RoyalHotel.model.Amenity;

public class AmenityService {
	
	private final DaoHelperFactory daoHelperFactory;

    public AmenityService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }
    
    public List<Amenity> findAll(int i18nId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
        	AmenityDaoApi dao = daoHelper.createAmenityDao();
            return dao.findAll(i18nId);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
                
    public void delete(int id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
        	AmenityDaoApi dao = daoHelper.createAmenityDao();
            dao.delete(id);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
    
    public Amenity save(Amenity amenity) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
        	AmenityDaoApi dao = daoHelper.createAmenityDao();       	
        	return (amenity.getId() > 0) ? dao.update(amenity) : dao.save(amenity);               	
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
         
    public boolean existsAmenityInRoomTypes(int id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
        	AmenityRoomTypeDaoApi dao = daoHelper.createAmenityRoomTypeDao();
            return dao.existsAmenity(id);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
    
}

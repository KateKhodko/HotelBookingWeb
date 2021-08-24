package com.khodko.RoyalHotel.service;

import java.sql.SQLException;
import java.util.Optional;

import com.khodko.RoyalHotel.dao.AmenityI18nDaoApi;
import com.khodko.RoyalHotel.dao.DaoHelper;
import com.khodko.RoyalHotel.dao.core.DaoHelperFactory;
import com.khodko.RoyalHotel.model.AmenityI18n;


public class AmenityI18nService {
	
	private final DaoHelperFactory daoHelperFactory;

    public AmenityI18nService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }
    
    public Optional<AmenityI18n> findByAmenity(int amenityId, int i18nId) throws ServiceException {
    	try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
    		AmenityI18nDaoApi dao = daoHelper.createAmenityI18nDao(); 
        	return dao.findByAmenity(amenityId, i18nId);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
    
    public boolean existsByName(AmenityI18n amenityI18n) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
        	AmenityI18nDaoApi dao = daoHelper.createAmenityI18nDao();
            return dao.existsByName(amenityI18n);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
    
    public AmenityI18n save(AmenityI18n amenityI18n) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
        	AmenityI18nDaoApi dao = daoHelper.createAmenityI18nDao();       	
        	return amenityI18n.getId() > 0 ? dao.update(amenityI18n) : dao.save(amenityI18n);               	
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
    
}

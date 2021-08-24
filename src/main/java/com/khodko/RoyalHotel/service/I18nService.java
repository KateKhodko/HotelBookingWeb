package com.khodko.RoyalHotel.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.khodko.RoyalHotel.dao.DaoHelper;
import com.khodko.RoyalHotel.dao.I18nDaoApi;
import com.khodko.RoyalHotel.dao.core.DaoHelperFactory;
import com.khodko.RoyalHotel.model.I18n;

public class I18nService {
	
	private final DaoHelperFactory daoHelperFactory;

    public I18nService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }
    
    public Optional<I18n> findById(int id) throws ServiceException {
    	try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
    		I18nDaoApi dao = daoHelper.createI18nDao(); 
        	return dao.findById(id);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
    
    public List<I18n> findAll() throws ServiceException {
    	try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
    		I18nDaoApi dao = daoHelper.createI18nDao(); 
        	return dao.findAll();
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
    
    public void delete(int id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
        	I18nDaoApi dao = daoHelper.createI18nDao();
            dao.delete(id);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
    
    public boolean existsByLocale(I18n i18n) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
        	I18nDaoApi dao = daoHelper.createI18nDao();
            return dao.existsByLocale(i18n);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
    
    public I18n save(I18n i18n) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
        	I18nDaoApi dao = daoHelper.createI18nDao();       	
        	return i18n.getId() > 0 ? dao.update(i18n) : dao.save(i18n);               	
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

}

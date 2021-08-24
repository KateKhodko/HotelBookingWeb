package com.khodko.RoyalHotel.service;

import java.sql.SQLException;
import java.util.Optional;

import com.khodko.RoyalHotel.dao.DaoHelper;
import com.khodko.RoyalHotel.dao.RememberedLoginDaoApi;
import com.khodko.RoyalHotel.dao.UserDaoApi;
import com.khodko.RoyalHotel.dao.core.DaoHelperFactory;
import com.khodko.RoyalHotel.form.PaymentForm;
import com.khodko.RoyalHotel.form.SignupForm;
import com.khodko.RoyalHotel.model.RememberedLogin;
import com.khodko.RoyalHotel.model.User;
import com.khodko.RoyalHotel.security.Token;


public class UserService {
	
	private final DaoHelperFactory daoHelperFactory;

    public UserService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }
    
    public User save(SignupForm signupForm) throws ServiceException {
        String password = Token.hash(signupForm.getPasswordValue());
        User user = new User(signupForm.getIdValue(), signupForm.getUsernameValue(), password, signupForm.getEmailValue());
        return save(user);
    }

    public void save(PaymentForm paymentForm, User user) throws ServiceException {
        User newUser = new User(paymentForm.getUserIdValue(), user.getUsername(), user.getPassword(), user.getEmail(), paymentForm.getFirstNameValue(), paymentForm.getLastNameValue(), paymentForm.getCountryValue(), paymentForm.getCardTypeValue(), paymentForm.getCardNumberValue());
        save(newUser);
    }
    
    public User save(User user) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
        	UserDaoApi dao = daoHelper.createUserDao();
        	return (user.getId() > 0) ? dao.update(user) : dao.save(user);               	
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
       
    public Optional<User> findByEmail(String email) throws ServiceException {
    	try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
        	UserDaoApi dao = daoHelper.createUserDao();
        	return dao.findByEmail(email);               	
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
    
    public Optional<User> findById(int id) throws ServiceException {
    	try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
        	UserDaoApi dao = daoHelper.createUserDao(); 
        	return dao.findByIdJoin(id);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
    
    public RememberedLogin saveRememberedLogin(RememberedLogin rememberedLogin) throws ServiceException {
    	try (DaoHelper daoHelper = daoHelperFactory.createDao()) {    		  		
    		RememberedLoginDaoApi dao = daoHelper.createRememberedLoginDao();
    		Optional<RememberedLogin> remLoginOpt = dao.findByUserId(rememberedLogin.getUserId());
    		if (remLoginOpt.isPresent()) {
    			return dao.update(rememberedLogin);
    		}    		
        	return dao.save(rememberedLogin);               	
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
    
    public Optional<RememberedLogin> findRememberLoginByToken(String token) throws ServiceException {
    	try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
    		RememberedLoginDaoApi dao = daoHelper.createRememberedLoginDao();
        	return dao.findByToken(token);               	
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
       
    public void deleteRememberedLogin(int userId) throws ServiceException {
    	try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
    		RememberedLoginDaoApi dao = daoHelper.createRememberedLoginDao();
        	dao.deleteByUserId(userId);               	
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

}

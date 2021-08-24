package com.khodko.RoyalHotel.localization;

import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khodko.RoyalHotel.config.Config;
import com.khodko.RoyalHotel.config.CookieConfig;
import com.khodko.RoyalHotel.config.Session;
import com.khodko.RoyalHotel.form.FormUtil;
import com.khodko.RoyalHotel.model.I18n;
import com.khodko.RoyalHotel.service.I18nService;
import com.khodko.RoyalHotel.service.ServiceException;
import com.khodko.RoyalHotel.service.ServiceFactory;


public class Localization {
    
	public static final int DEFAULT_I18N = 1;
    public static final String I18N_ATTR = "i18n";
    public static final String I18NS_ATTR = "i18ns";
    
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final I18nService i18nService;
    
    public Localization(HttpServletRequest request, HttpServletResponse response) {
    	this.request = request;
    	this.response = response;
    	this.i18nService = new ServiceFactory().createI18nService();
    	setAllI18nToRequest();
    }
    
    public static int getI18nId(HttpServletRequest request) {
    	I18n i18n = (I18n) request.getAttribute(Localization.I18N_ATTR);				
		return i18n != null ? i18n.getId() : 1;
    }
         
    public I18n getI18n() {							
		try {
			I18n sessionI18n = (I18n) request.getSession().getAttribute(Session.SESSION_I18N);
			if (sessionI18n == null) return getI18nFromCookie();
			return getI18n(sessionI18n.getId());
		} catch (ServiceException e) {
		}
		return null;
	}
    
    public I18n getI18n(int id) {
    	try {
			Optional<I18n> i18nOpt = i18nService.findById(id);
			if (i18nOpt.isPresent()) {
				I18n i18n = i18nOpt.get();
				save(i18n);
				return i18n;
			}	
    	} catch (ServiceException e) {
		}		
		return null;
	}       
    
    private void setAllI18nToRequest() {   	
    	try {
    		request.setAttribute(I18NS_ATTR, i18nService.findAll()); 
    	} catch (ServiceException e) {
		}		
    }
    
    private I18n getI18nFromCookie() throws ServiceException {
    	Optional<Cookie> optionalCookie = CookieConfig.getByName(request, Config.COOKIE_I18N);
    	I18n i18n = null;
        if (optionalCookie.isPresent()) {
        	int i18nId = FormUtil.parseUnsignedInt(optionalCookie.get().getValue());
        	if (i18nId > 0) {
        		i18n = getI18n(i18nId);       		
        	}       	
        } 
        return i18n;
    }
    
    private void save(I18n i18n) {
    	saveToCookie(i18n);
    	saveToSession(i18n);
    	saveToRequest(i18n);   	
    }
    
    private void saveToCookie(I18n i18n) {
    	CookieConfig.setCookie(Config.COOKIE_I18N, String.valueOf(i18n.getId()), response);
    }
    
    private void saveToSession(I18n i18n) {
		request.getSession().setAttribute(Session.SESSION_I18N, i18n);
	}
           
    private void saveToRequest(I18n i18n) {
		request.setAttribute(I18N_ATTR, i18n);
	}
       
}

package com.khodko.RoyalHotel.security;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.khodko.RoyalHotel.config.Config;
import com.khodko.RoyalHotel.config.Session;
import com.khodko.RoyalHotel.model.RememberedLogin;
import com.khodko.RoyalHotel.model.User;
import com.khodko.RoyalHotel.service.ServiceException;
import com.khodko.RoyalHotel.service.ServiceFactory;
import com.khodko.RoyalHotel.service.UserService;
import com.khodko.RoyalHotel.config.CookieConfig;
import com.khodko.RoyalHotel.util.SessionUtil;

public class Auth {
								
	public static User getUser(HttpServletRequest request) {							
		try {
			User sessionUser = (User) request.getSession().getAttribute(Session.SESSION_USER);
			if (sessionUser == null) return loginFromRememberCookie(request);
			return getUser(sessionUser.getId(), request);
		} catch (ServiceException e) {
		}
		return null;
	}
	
	public static User loginFromRememberCookie(HttpServletRequest request) throws ServiceException {
		Cookie[] cookies = request.getCookies();
		String token = CookieConfig.getCookie(cookies, Config.TOKEN_COOKIE);
		if (!token.isEmpty()) {
			UserService userService =  new ServiceFactory().createUserService();
			Optional<RememberedLogin> rememberedLoginOpt = userService.findRememberLoginByToken(token);
			if (rememberedLoginOpt.isPresent()) {					
				RememberedLogin rememberedLogin = rememberedLoginOpt.get();	
				if (rememberedLogin.getExpiereAt().after(new Date())) {
					return getUser(rememberedLogin.getUserId(), request);	
				}											
			}	
		}
		return null;
	}
		
	public static User getUser(int id, HttpServletRequest request) throws ServiceException {
		UserService userService = new ServiceFactory().createUserService();
		Optional<User> userOpt = userService.findById(id);
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			login(request, user);
			return user;
		}	
		return null;
	}
	
	public static void login(HttpServletRequest request, User user) {
		SessionUtil.regenerateSession(request);
		request.getSession().setAttribute(Session.SESSION_USER, user);
	}
				
	public static void rememberRequestedPage(HttpServletRequest request) {
		String requestUri = request.getRequestURI();		
		request.getSession().setAttribute(Session.SESSION_REQUEST_URI, requestUri);
	}
		
	public static Object getRememberRequestedPage(HttpServletRequest request) {		
		return request.getSession().getAttribute(Session.SESSION_REQUEST_URI);
	}

}

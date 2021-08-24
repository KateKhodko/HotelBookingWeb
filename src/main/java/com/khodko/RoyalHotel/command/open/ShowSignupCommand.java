package com.khodko.RoyalHotel.command.open;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.config.Pages;
import com.khodko.RoyalHotel.config.Session;
import com.khodko.RoyalHotel.form.SignupForm;
import com.khodko.RoyalHotel.service.ServiceException;

public class ShowSignupCommand implements Command {
	
	private final static Logger LOGGER = LogManager.getLogger(ShowSignupCommand.class);
	
	public static final String MESSAGES_ATTR = "messages";
	public static final String SIGNUP_FORM_ATTR = "signupForm";
	
	@Override
	public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		setSessionData(request);
		return CommandResults.forward(Pages.SIGNUP);
	}
	
	private void setSessionData(HttpServletRequest request) {
		Object messagesObj = request.getSession().getAttribute(Session.SESSION_MESSAGES);
		if (messagesObj instanceof List<?>) {
			request.setAttribute(MESSAGES_ATTR, (List<?>) messagesObj);
			request.getSession().removeAttribute(Session.SESSION_MESSAGES);
		}
		
		Object signupFormObj =  request.getSession().getAttribute(Session.SESSION_SIGNUP_FORM);
		if (signupFormObj instanceof SignupForm) {
			request.setAttribute(SIGNUP_FORM_ATTR, (SignupForm) signupFormObj);
			request.getSession().removeAttribute(Session.SESSION_SIGNUP_FORM);
		}
	}

}

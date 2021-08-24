package com.khodko.RoyalHotel.command.open;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khodko.RoyalHotel.config.Path;
import com.khodko.RoyalHotel.config.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.service.ServiceException;
import com.khodko.RoyalHotel.service.UserService;
import com.khodko.RoyalHotel.form.SignupForm;


public class SignupCommand implements Command {
	
	private final static Logger LOGGER = LogManager.getLogger(SignupCommand.class);
				
	private final UserService userService;
	
	public SignupCommand(UserService userService) {
        this.userService = userService;
    }

	@Override
	public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {			
		SignupForm signupForm = new SignupForm(request);
		List<String> messages = signupForm.validate();
		if (userService.findByEmail(signupForm.getEmailValue()).isPresent()) {
			messages.add("locale.message.emailexists");
		}
		if (messages.isEmpty()) {		
			userService.save(signupForm);
			return CommandResults.redirectPath(request, Path.PUBLIC_SHOW_LOGIN);
		}
		
		request.getSession().setAttribute(Session.SESSION_SIGNUP_FORM, signupForm);
		request.getSession().setAttribute(Session.SESSION_MESSAGES, messages);
		return CommandResults.redirectPath(request, Path.PUBLIC_SHOW_SIGNUP);				
	}
			
}

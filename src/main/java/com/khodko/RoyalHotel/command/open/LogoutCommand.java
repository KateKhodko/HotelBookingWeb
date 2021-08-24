package com.khodko.RoyalHotel.command.open;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khodko.RoyalHotel.config.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.model.User;
import com.khodko.RoyalHotel.security.Auth;
import com.khodko.RoyalHotel.service.ServiceException;
import com.khodko.RoyalHotel.service.UserService;
import com.khodko.RoyalHotel.config.CookieConfig;

public class LogoutCommand implements Command {
	
	private final static Logger LOGGER = LogManager.getLogger(LogoutCommand.class);
	
	private final UserService userService;

	public LogoutCommand(UserService userService) {
        this.userService = userService;
    }

	@Override
	public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		User user = Auth.getUser(request);
		if (user != null) {
			userService.deleteRememberedLogin(user.getId());
		}		
		request.getSession().invalidate();	
		CookieConfig.removeCookie(request, response);
		return CommandResults.redirectPath(request, Path.PUBLIC_HOME);
	}

}

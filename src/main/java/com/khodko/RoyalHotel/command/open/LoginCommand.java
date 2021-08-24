package com.khodko.RoyalHotel.command.open;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khodko.RoyalHotel.config.CookieConfig;
import com.khodko.RoyalHotel.config.Path;
import com.khodko.RoyalHotel.config.Session;
import com.khodko.RoyalHotel.form.LoginForm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.khodko.RoyalHotel.config.Config;
import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.model.RememberedLogin;
import com.khodko.RoyalHotel.model.User;
import com.khodko.RoyalHotel.security.Auth;
import com.khodko.RoyalHotel.security.Token;
import com.khodko.RoyalHotel.service.ServiceException;
import com.khodko.RoyalHotel.service.UserService;


public class LoginCommand implements Command {
	
	private final static Logger LOGGER = LogManager.getLogger(LoginCommand.class);
		
	private final UserService userService;
	
	public LoginCommand(UserService userService) {
        this.userService = userService;
    }

	@Override
	public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		LoginForm loginForm = new LoginForm(request);
		List<String> messages = loginForm.validate();
		if (messages.isEmpty()) {
			Optional<User> userOptional = userService.findByEmail(loginForm.getEmailValue());
			if (userOptional.isPresent()) {				
				User dbUser = userOptional.get();
				
				String hashPassword = Token.hash(loginForm.getPasswordValue());
				if (hashPassword.equals(dbUser.getPassword())) {
					
					Auth.login(request, dbUser);
					
					if (!loginForm.getRememberMeValue().isEmpty()) {
						rememberLogin(dbUser, request, response);
					}
										
					Object sessionRequestUri = Auth.getRememberRequestedPage(request);
					if (sessionRequestUri != null) {
						return CommandResults.redirectUri(sessionRequestUri.toString());
					}						
					return CommandResults.redirectPath(request, Path.PUBLIC_HOME);
				} 														 
			} 
			messages.add("locale.message.authorizationerror");
		}
				
		request.getSession().setAttribute(Session.SESSION_LOGIN_FORM, loginForm);
		request.getSession().setAttribute(Session.SESSION_MESSAGES, messages);
		return CommandResults.redirectPath(request, Path.PUBLIC_SHOW_LOGIN);
	}
	
	private void rememberLogin(User user, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		Token token = new Token();
		
		String hashedToken = token.getHash();
		Date date = Date.from(Instant.now().plusSeconds(Config.TOKEN_EXPIRE));											
		Timestamp expiresAt = new Timestamp(date.getTime());
		
		RememberedLogin rememberedLogin = new RememberedLogin(hashedToken, user.getId(), expiresAt);
		userService.saveRememberedLogin(rememberedLogin);

		CookieConfig.setCookie(Config.TOKEN_COOKIE, hashedToken, response);
	}
						
}

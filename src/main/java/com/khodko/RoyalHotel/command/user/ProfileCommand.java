package com.khodko.RoyalHotel.command.user;

import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.config.Config;
import com.khodko.RoyalHotel.config.Pages;
import com.khodko.RoyalHotel.config.Path;
import com.khodko.RoyalHotel.config.Session;
import com.khodko.RoyalHotel.form.ProfileForm;
import com.khodko.RoyalHotel.model.User;
import com.khodko.RoyalHotel.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.khodko.RoyalHotel.config.Session.SESSION_INFO_MESSAGE;

import java.util.List;

public class ProfileCommand implements Command {

	public static final String MESSAGES_ATTR = "messages";
    public static final String PROFILE_FORM_ATTR = "profileForm";
    public static final String INFO_MESSAGE_ATTR = "infoMessage";

    public ProfileCommand() {
    }

    @Override
    public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String infoMessage = (String) request.getSession().getAttribute(SESSION_INFO_MESSAGE);
        if (infoMessage != null) {
            request.setAttribute(INFO_MESSAGE_ATTR, infoMessage);
            request.getSession().removeAttribute(SESSION_INFO_MESSAGE);
        }

        User user = (User) request.getAttribute(Config.USER_REQUEST_ATTR);
        if (user == null) {
            return CommandResults.redirectPath(request, Path.PUBLIC_LOGIN);
        }
        
        ProfileForm profileForm = setProfileFormFromSession(request);
        if (profileForm != null) {
        	setMessagesFromSession(request);
        } else {
        	profileForm = new ProfileForm(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getCountry(), user.getCardType(), user.getCardNumber());
        }
        request.setAttribute(PROFILE_FORM_ATTR, profileForm);

        return CommandResults.forward(Pages.USER_PROFILE);
    }
    
    private void setMessagesFromSession(HttpServletRequest request) {
		Object messagesObj = request.getSession().getAttribute(Session.SESSION_MESSAGES);
		if (messagesObj instanceof List<?>) {
			request.setAttribute(MESSAGES_ATTR, (List<?>) messagesObj);
			request.getSession().removeAttribute(Session.SESSION_MESSAGES);
		}		
	}
	
	private ProfileForm setProfileFormFromSession(HttpServletRequest request) {
		ProfileForm profilForm = null;
		Object profileFormObj = request.getSession().getAttribute(Session.SESSION_PROFILE_FORM);
		if (profileFormObj instanceof ProfileForm) {
			profilForm = (ProfileForm) profileFormObj;			
			request.getSession().removeAttribute(Session.SESSION_PROFILE_FORM);
		}
		return profilForm;
	}
}

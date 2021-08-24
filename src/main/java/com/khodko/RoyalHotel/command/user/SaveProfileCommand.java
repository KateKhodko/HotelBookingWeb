package com.khodko.RoyalHotel.command.user;

import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.config.Path;
import com.khodko.RoyalHotel.config.Session;
import com.khodko.RoyalHotel.form.ProfileForm;
import com.khodko.RoyalHotel.model.User;
import com.khodko.RoyalHotel.security.Token;
import com.khodko.RoyalHotel.service.ServiceException;
import com.khodko.RoyalHotel.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.khodko.RoyalHotel.config.Config.USER_REQUEST_ATTR;
import static com.khodko.RoyalHotel.config.Session.SESSION_INFO_MESSAGE;

public class SaveProfileCommand implements Command {

    private final UserService userService;

    public SaveProfileCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        User user = (User) request.getAttribute(USER_REQUEST_ATTR);
        ProfileForm profileForm = new ProfileForm(request);
        List<String> messages = profileForm.validate();
        if (user == null || user.getId() != profileForm.getUserIdValue()) {
            return CommandResults.redirectPath(request, Path.PUBLIC_LOGIN);
        }

        if (messages.isEmpty()) {
            String password = user.getPassword();
            if (!profileForm.getNewPasswordValue().isEmpty()) {
                String oldHashPassword = Token.hash(profileForm.getOldPasswordValue());
                if (!oldHashPassword.equals(user.getPassword())) {
                    messages.add("locale.message.invalidoldpass");
                }
                password = Token.hash(profileForm.getNewPasswordValue());
            }
            User newUser = new User(user.getId(), profileForm.getUsernameValue(), password, user.getEmail(), profileForm.getFirstNameValue(), profileForm.getLastNameValue(), profileForm.getCountryValue(), profileForm.getCardTypeValue(), profileForm.getCardNumberValue());
            userService.save(newUser);
            request.getSession().setAttribute(SESSION_INFO_MESSAGE, "locale.message.profilesaved");
            return CommandResults.redirectPath(request, Path.USER_PROFILE);
        }
       
        request.getSession().setAttribute(Session.SESSION_PROFILE_FORM, profileForm);
		request.getSession().setAttribute(Session.SESSION_MESSAGES, messages);
		return CommandResults.redirectPath(request, Path.USER_PROFILE);
    }
}

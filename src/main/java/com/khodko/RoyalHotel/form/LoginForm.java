package com.khodko.RoyalHotel.form;

import javax.servlet.http.HttpServletRequest;

public class LoginForm extends BaseForm {

    public final static String emailName = "email";
    public final static String passwordName = "password";
    public final static String rememberMeName = "rememberme";

    private final String emailValue;
    private final String passwordValue;
    private final String rememberMeValue;

    public LoginForm(HttpServletRequest request) {
        emailValue = FormUtil.trim(request.getParameter(emailName));
        passwordValue = FormUtil.trim(request.getParameter(passwordName));
        rememberMeValue = !FormUtil.trim(request.getParameter(rememberMeName)).equals("") ? "checked" : "";
    }

    @Override
    protected void runValidation() {
        if (emailValue.isEmpty()) {
            messages.add("locale.message.emptyusername");
        }
        if (passwordValue.isEmpty()) {
            messages.add("locale.message.emptypassword");
        }
    }

    public String getEmailValue() {
        return emailValue;
    }

    public String getPasswordValue() {
        return passwordValue;
    }

    public String getRememberMeValue() {
        return rememberMeValue;
    }
}

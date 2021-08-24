package com.khodko.RoyalHotel.form;

import javax.servlet.http.HttpServletRequest;


public class SignupForm extends BaseForm {

	public static final String idName = "id";
	public static final String usernameName = "username";
	public static final String emailName = "email";
	public static final String passwordName = "password";
	public static final String passwordRepeatName = "password_repeat";
	
	public final int idValue;
	public final String usernameValue;
	public final String emailValue;
	public final String passwordValue;
	public final String passwordRepeatValue;
	
	public SignupForm(HttpServletRequest request) {
		idValue = FormUtil.parseUnsignedInt(request.getParameter(idName));
		usernameValue = FormUtil.trim(request.getParameter(usernameName));
		emailValue = FormUtil.trim(request.getParameter(emailName));
		passwordValue = FormUtil.trim(request.getParameter(passwordName));
		passwordRepeatValue = FormUtil.trim(request.getParameter(passwordRepeatName));
	}

	@Override
	public void runValidation() {
		if (usernameValue.isEmpty()) {
			messages.add("locale.message.emptyusername");
		}
		if (emailValue.isEmpty()) {
			messages.add("locale.message.emptyemail");
		} 
		if (passwordValue.isEmpty()) {
			messages.add("locale.message.emptypassword");
		}
		if (passwordRepeatValue.isEmpty()) {
			messages.add("locale.message.emptypassconf");
		}
		if (!passwordValue.equals(passwordRepeatValue)) {
			messages.add("locale.message.invalidpassconf");
		}
	}

	public int getIdValue() {
		return idValue;
	}

	public String getUsernameValue() {
		return usernameValue;
	}

	public String getEmailValue() {
		return emailValue;
	}

	public String getPasswordValue() {
		return passwordValue;
	}

	public String getPasswordRepeatValue() {
		return passwordRepeatValue;
	}
		
}

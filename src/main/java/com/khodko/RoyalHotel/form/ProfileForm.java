package com.khodko.RoyalHotel.form;

import javax.servlet.http.HttpServletRequest;

public class ProfileForm extends BaseForm {

    public final static String userIdName = "userId";
    public final static String usernameName = "username";
    public final static String firstNameName = "firstName";
    public final static String lastNameName = "lastName";
    public final static String countryName = "country";
    public final static String cardTypeName = "cardType";
    public final static String cardNumberName = "cardNumber";
    public final static String newPasswordName = "newPassword";
    public final static String repeatNewPasswordName = "repeatNewPassword";
    public final static String oldPasswordName = "oldPassword";

    private final int userIdValue;
    private final String usernameValue;
    private final String firstNameValue;
    private final String lastNameValue;
    private final String countryValue;
    private final String cardTypeValue;
    private final String cardNumberValue;
    private String newPasswordValue;
    private String repeatNewPasswordValue;
    private String oldPasswordValue;

    public ProfileForm(HttpServletRequest request) {
        userIdValue = FormUtil.parseUnsignedInt(request.getParameter(userIdName));
        usernameValue = FormUtil.trim(request.getParameter(usernameName));
        firstNameValue = FormUtil.trim(request.getParameter(firstNameName));
        lastNameValue = FormUtil.trim(request.getParameter(lastNameName));
        countryValue = FormUtil.trim(request.getParameter(countryName));
        cardTypeValue = FormUtil.trim(request.getParameter(cardTypeName));
        cardNumberValue = FormUtil.trim(request.getParameter(cardNumberName));
        newPasswordValue = FormUtil.trim(request.getParameter(newPasswordName));
        repeatNewPasswordValue = FormUtil.trim(request.getParameter(repeatNewPasswordName));
        oldPasswordValue = FormUtil.trim(request.getParameter(oldPasswordName));
    }

    public ProfileForm(int userIdValue, String usernameValue, String firstNameValue, String lastNameValue, String countryValue, String cardTypeValue, String cardNumberValue) {
        this.userIdValue = userIdValue;
        this.usernameValue = usernameValue;
        this.firstNameValue = firstNameValue;
        this.lastNameValue = lastNameValue;
        this.countryValue = countryValue;
        this.cardTypeValue = cardTypeValue;
        this.cardNumberValue = cardNumberValue;
    }

    @Override
    protected void runValidation() {
        if (usernameValue.isEmpty()) {
            messages.add("locale.message.emptyusername");
        }
        if (firstNameValue.isEmpty()) {
            messages.add("locale.message.inputfirstname");
        }
        if (lastNameValue.isEmpty()) {
            messages.add("locale.message.inputlastname");
        }
        if (countryValue.isEmpty()) {
            messages.add("locale.message.inputcountry");
        }
        if (cardTypeValue.isEmpty()) {
            messages.add("locale.message.inputcardtype");
        }
        if (cardNumberValue.isEmpty()) {
            messages.add("locale.message.inputcardnumber");
        }
        if (!newPasswordValue.isEmpty()) {
            if (!newPasswordValue.equals(repeatNewPasswordValue)) {
                messages.add("locale.message.inputcorrectpass");
            }
            if (oldPasswordValue.isEmpty()) {
                messages.add("locale.message.inputoldpass");
            }
        }
    }

    public int getUserIdValue() {
        return userIdValue;
    }

    public String getUsernameValue() {
        return usernameValue;
    }

    public String getFirstNameValue() {
        return firstNameValue;
    }

    public String getLastNameValue() {
        return lastNameValue;
    }

    public String getCountryValue() {
        return countryValue;
    }

    public String getCardTypeValue() {
        return cardTypeValue;
    }

    public String getCardNumberValue() {
        return cardNumberValue;
    }

    public String getNewPasswordValue() {
        return newPasswordValue;
    }

    public String getRepeatNewPasswordValue() {
        return repeatNewPasswordValue;
    }

    public String getOldPasswordValue() {
        return oldPasswordValue;
    }
}

package com.khodko.RoyalHotel.form;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

public class PaymentForm extends BaseForm {

    public final static String userIdName = "userId";
    public final static String firstNameName = "firstName";
    public final static String lastNameName = "lastName";
    public final static String countryName = "country";
    public final static String cardTypeName = "cardType";
    public final static String cardNumberName = "cardNumber";
    public final static String secureCodeName = "secureCode";
    public final static String expirationMonthName = "expirationMonth";
    public final static String expirationYearName = "expirationYear";

    private final int userIdValue;
    private final String firstNameValue;
    private final String lastNameValue;
    private final String countryValue;
    private final String cardTypeValue;
    private final String cardNumberValue;
    private int secureCodeValue;
    private int expirationMonthValue = Calendar.getInstance().get(Calendar.MONTH);
    private int expirationYearValue = Calendar.getInstance().get(Calendar.YEAR);

    public PaymentForm(HttpServletRequest request) {
        userIdValue = FormUtil.parseUnsignedInt(request.getParameter(userIdName));
        firstNameValue = FormUtil.trim(request.getParameter(firstNameName));
        lastNameValue = FormUtil.trim(request.getParameter(lastNameName));
        countryValue = FormUtil.trim(request.getParameter(countryName));
        cardTypeValue = FormUtil.trim(request.getParameter(cardTypeName));
        cardNumberValue = FormUtil.trim(request.getParameter(cardNumberName));
        secureCodeValue = FormUtil.parseUnsignedInt(request.getParameter(secureCodeName));
        expirationMonthValue = FormUtil.parseUnsignedInt(request.getParameter(expirationMonthName));
        expirationYearValue = FormUtil.parseUnsignedInt(request.getParameter(expirationYearName));
    }

    public PaymentForm(int userIdValue, String firstNameValue, String lastNameValue, String countryValue, String cardTypeValue, String cardNumberValue) {
        this.userIdValue = userIdValue;
        this.firstNameValue = firstNameValue;
        this.lastNameValue = lastNameValue;
        this.countryValue = countryValue;
        this.cardTypeValue = cardTypeValue;
        this.cardNumberValue = cardNumberValue;
    }

    @Override
    protected void runValidation() {
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
        if (secureCodeValue == 0) {
            messages.add("locale.message.inputsecurecode");
        }
        if (expirationMonthValue < 1 || expirationMonthValue > 12) {
            messages.add("locale.message.inputmonth");
        }
        int year = Calendar.getInstance().get(Calendar.YEAR);
        if (expirationYearValue < year || expirationYearValue > year + 10) {
            messages.add("locale.message.inputyear");
        }
        if (expirationYearValue == year && expirationMonthValue < Calendar.getInstance().get(Calendar.MONTH)) {
            messages.add("locale.message.inputexpdate");
        }
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

    public int getSecureCodeValue() {
        return secureCodeValue;
    }

    public int getExpirationMonthValue() {
        return expirationMonthValue;
    }

    public int getExpirationYearValue() {
        return expirationYearValue;
    }

    public int getUserIdValue() {
        return userIdValue;
    }
}

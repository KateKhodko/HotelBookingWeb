package com.khodko.RoyalHotel.form;

import javax.servlet.http.HttpServletRequest;


public class AmenityForm extends BaseForm {

    public static final String idName = "id";
    public static final String nameName = "name";
    public static final String priceName = "price";
    public static final String i18nIdName = "i18nId";

    private final int idValue; 
    private final String nameValue;
    private final int priceValue;
    private final int i18nIdValue;

    public AmenityForm(HttpServletRequest request) {
        idValue = FormUtil.parseUnsignedInt(request.getParameter(idName));
        nameValue = FormUtil.trim(request.getParameter(nameName));
        priceValue = FormUtil.parseUnsignedInt(request.getParameter(priceName));
        i18nIdValue = FormUtil.parseUnsignedInt(request.getParameter(i18nIdName));
    }

    @Override
    protected void runValidation() {
        if (nameValue.isEmpty()) {
            messages.add("locale.message.emptyname");
        }
        if (priceValue == 0) {
            messages.add("locale.message.requiredprice");
        }
    }

    public int getIdValue() {
        return idValue;
    }

    public String getNameValue() {
        return nameValue;
    }

    public int getPriceValue() {
        return priceValue;
    }

	public int getI18nIdValue() {
		return i18nIdValue;
	}      
}

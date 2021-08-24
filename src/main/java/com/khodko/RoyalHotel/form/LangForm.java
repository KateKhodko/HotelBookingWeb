package com.khodko.RoyalHotel.form;

import javax.servlet.http.HttpServletRequest;


public class LangForm extends BaseForm {

    public static final String idName = "id";
    public static final String localeName = "locale";
    public static final String nameName = "name";

    private final int idValue; 
    private final String localeValue;
    private final String nameValue;
    
    public LangForm(HttpServletRequest request) {
        idValue = FormUtil.parseUnsignedInt(request.getParameter(idName));
        localeValue = FormUtil.trim(request.getParameter(localeName)); 
        nameValue = FormUtil.trim(request.getParameter(nameName));      
    }

    @Override
    protected void runValidation() {
        if (localeValue.isEmpty()) {
            messages.add("Locale is empty");
        }
        if (nameValue.isEmpty()) {
            messages.add("Name is empty");
        }
    }

    public int getIdValue() {
        return idValue;
    }

    public String getNameValue() {
        return nameValue;
    }

	public String getLocaleValue() {
		return localeValue;
	}
        
}

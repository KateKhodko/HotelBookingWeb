package com.khodko.RoyalHotel.form;

import javax.servlet.http.HttpServletRequest;

public class AmenityI18nForm extends BaseForm {
	
	public static final String idName = "id";
	public static final String amenityIdName = "amenityId";
	public static final String i18nIdName = "i18Id";
	public static final String nameName = "name";
	
	private int idValue;
	private final int amenityIdValue;
	private final int i18nIdValue;
	private final String nameValue;
	
	public AmenityI18nForm(HttpServletRequest request) {
		idValue = FormUtil.parseUnsignedInt(request.getParameter(idName));
		amenityIdValue = FormUtil.parseUnsignedInt(request.getParameter(amenityIdName));
		i18nIdValue = FormUtil.parseUnsignedInt(request.getParameter(i18nIdName));		
		nameValue = FormUtil.trim(request.getParameter(nameName));		
	}
		
	public AmenityI18nForm(int idValue, int amenityIdValue, int i18nIdValue, String nameValue) {
		this.idValue = idValue;
		this.amenityIdValue = amenityIdValue;
		this.i18nIdValue = i18nIdValue;
		this.nameValue = nameValue;
	}

	@Override
	public void runValidation() {
		if (nameValue.isEmpty()) {
            messages.add("Field name is empty");
        }
		if (amenityIdValue == 0) {
			messages.add("Amenity id is empty");
		}
		if (i18nIdValue == 0) {
			messages.add("Locale is empty");
		}
	}
		
	public void setIdValue(int idValue) {
		this.idValue = idValue;
	}

	public int getIdValue() {
		return idValue;
	}

	public int getAmenityIdValue() {
		return amenityIdValue;
	}

	public int getI18nIdValue() {
		return i18nIdValue;
	}

	public String getNameValue() {
		return nameValue;
	}
		
}

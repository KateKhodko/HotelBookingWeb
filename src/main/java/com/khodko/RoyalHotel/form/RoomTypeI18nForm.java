package com.khodko.RoyalHotel.form;

import javax.servlet.http.HttpServletRequest;

public class RoomTypeI18nForm extends BaseForm {
	
	public static final String idName = "id";
	public static final String roomTypeIdName = "roomTypeId";
	public static final String i18nIdName = "i18nId";
	public static final String nameName = "name";
	public static final String descriptionName = "description";
	
	private int idValue;
	private final int roomTypeIdValue;
	private final int i18nIdValue;
	private final String nameValue;
	private final String descriptionValue;
		
	public RoomTypeI18nForm(HttpServletRequest request) {
		idValue = FormUtil.parseUnsignedInt(request.getParameter(idName));
		roomTypeIdValue = FormUtil.parseUnsignedInt(request.getParameter(roomTypeIdName));
		i18nIdValue = FormUtil.parseUnsignedInt(request.getParameter(i18nIdName));		
		nameValue = FormUtil.trim(request.getParameter(nameName));	
		descriptionValue = FormUtil.trim(request.getParameter(descriptionName));	
	}
		
	public RoomTypeI18nForm(int idValue, int roomTypeIdValue, int i18nIdValue, String nameValue, String descriptionValue) {
		this.idValue = idValue;
		this.roomTypeIdValue = roomTypeIdValue;
		this.i18nIdValue = i18nIdValue;
		this.nameValue = nameValue;
		this.descriptionValue = descriptionValue;
	}

	@Override
	public void runValidation() {
		if (nameValue.isEmpty()) {
            messages.add("Field name is empty");
        }
		if (roomTypeIdValue == 0) {
			messages.add("Room type id is empty");
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

	public int getRoomTypeIdValue() {
		return roomTypeIdValue;
	}

	public int getI18nIdValue() {
		return i18nIdValue;
	}

	public String getNameValue() {
		return nameValue;
	}

	public String getDescriptionValue() {
		return descriptionValue;
	}
				
}

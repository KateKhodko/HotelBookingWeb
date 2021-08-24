package com.khodko.RoyalHotel.form;

import javax.servlet.http.HttpServletRequest;

public class RoomTypeForm extends BaseForm {

    public static final String idName = "id";
    public static final String nameName = "name";
    public static final String priceName = "price";
    public static final String occupancyName = "occupancy";
    public static final String descriptionName = "description";
    public static final String imageName = "image";
    public static final String sizeName = "size";
    public static final String roomsName = "rooms";
    public static final String accessName = "access";
    public static final String roomTypeI18nIdName = "roomTypeI18nId";

    private final int idValue;
    private final String nameValue;
    private final int occupancyValue;
    private final String descriptionValue;
    private final String imageValue;
    private final int sizeValue;
    private final int priceValue;
    private final int roomsValue;
    private final boolean accessValue;
    private final int roomTypeI18nIdValue;

    public RoomTypeForm(int idValue, String nameValue, int occupancyValue, String descriptionValue, String imageValue, int sizeValue, int priceValue, int roomsValue, int roomTypeI18nIdValue, boolean accessValue) {
        this.idValue = idValue;
        this.nameValue = nameValue;
        this.occupancyValue = occupancyValue;
        this.descriptionValue = descriptionValue;
        this.imageValue = imageValue;
        this.sizeValue = sizeValue;
        this.priceValue = priceValue;
        this.roomsValue = roomsValue;
        this.accessValue = accessValue;
        this.roomTypeI18nIdValue = roomTypeI18nIdValue;
    }
    public RoomTypeForm(HttpServletRequest request) {
        idValue = FormUtil.parseUnsignedInt(request.getParameter(idName));
        nameValue = FormUtil.trim(request.getParameter(nameName));
        occupancyValue = FormUtil.parseUnsignedInt(request.getParameter(occupancyName));
        descriptionValue = FormUtil.trim(request.getParameter(descriptionName));
        imageValue = FormUtil.trim(request.getParameter(imageName));
        sizeValue = FormUtil.parseUnsignedInt(request.getParameter(sizeName));
        priceValue = FormUtil.parseUnsignedInt(request.getParameter(priceName));
        roomsValue = FormUtil.parseUnsignedInt(request.getParameter(roomsName));              
        accessValue = request.getParameterValues(accessName) != null;
        roomTypeI18nIdValue = FormUtil.parseUnsignedInt(request.getParameter(roomTypeI18nIdName));
    }

    @Override
    protected void runValidation() {
        if (nameValue.isEmpty()) {
            messages.add("locale.message.emptyroomname");
        }
        if (occupancyValue == 0) {
            messages.add("locale.message.occupancyrequired");
        }
        if (priceValue == 0) {
            messages.add("locale.message.pricerequired");
        }
    }

    public int getIdValue() {
        return idValue;
    }

    public String getNameValue() {
        return nameValue;
    }

    public int getOccupancyValue() {
        return occupancyValue;
    }

    public String getDescriptionValue() {
        return descriptionValue;
    }

    public String getImageValue() {
        return imageValue;
    }

    public int getSizeValue() {
        return sizeValue;
    }

    public int getPriceValue() {
        return priceValue;
    }
       
    public boolean isAccessValue() {
		return accessValue;
	}
	public int getRoomsValue() {
        return roomsValue;
    }
    
	public int getRoomTypeI18nIdValue() {
		return roomTypeI18nIdValue;
	}
        
}

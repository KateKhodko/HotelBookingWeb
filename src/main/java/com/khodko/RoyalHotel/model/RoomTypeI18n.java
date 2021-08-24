package com.khodko.RoyalHotel.model;

public class RoomTypeI18n extends IdEntity {
	
    private Integer roomTypeId;
    private Integer i18nId;
    private String name;
    private String description;
    
    public RoomTypeI18n() {
    	super();
    }
    
	public RoomTypeI18n(Integer roomTypeId, Integer i18nId, String name, String description) {
		super();
		this.roomTypeId = roomTypeId;
		this.i18nId = i18nId;
		this.name = name;
		this.description = description;
	}
    
	public RoomTypeI18n(Integer id, Integer roomTypeId, Integer i18nId, String name, String description) {
		this(roomTypeId, i18nId, name, description);
		this.id = id;
	}

	public Integer getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Integer roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public Integer getI18nId() {
		return i18nId;
	}

	public void setI18nId(Integer i18nId) {
		this.i18nId = i18nId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "RoomTypeI18n [id=" + id + ", roomTypeId=" + roomTypeId + ", i18nId=" + i18nId + ", name=" + name
				+ ", description=" + description + "]";
	}
		
}

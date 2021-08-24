package com.khodko.RoyalHotel.model;


public class AmenityI18n extends IdEntity {

    private int amenityId;
    private int i18nId;
    private String name;
    
    public AmenityI18n() {
    	super();
    }
         
	public AmenityI18n(int amenityId, int i18nId, String name) {
		super();
		this.amenityId = amenityId;
		this.i18nId = i18nId;
		this.name = name;
	}
	
	public AmenityI18n(int id, int amenityId, int i18nId, String name) {
		this(amenityId, i18nId, name);
		this.id = id;
	}

	public int getAmenityId() {
		return amenityId;
	}

	public void setAmenityId(int amenityId) {
		this.amenityId = amenityId;
	}

	public int getI18nId() {
		return i18nId;
	}

	public void setI18nId(int i18nId) {
		this.i18nId = i18nId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AmenityI18n [id=" + id + ", amenityId=" + amenityId + ", i18nId=" + i18nId + ", name=" + name + "]";
	}
        
}

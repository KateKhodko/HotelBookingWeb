package com.khodko.RoyalHotel.model;

public class Amenity extends IdEntity {

    private Integer price;
    
    private AmenityI18n amenityI18n;
    
    public Amenity() { 
    	super();
    }

    public Amenity(Integer price) {
    	super();
        this.price = price;
    }

    public Amenity(Integer id, Integer price) {
    	super();
        this.id = id;
        this.price = price;
    }
       
	public void setPrice(Integer price) {
		this.price = price;
	}

    public Integer getPrice() {
        return price;
    }
    
    public AmenityI18n getAmenityI18n() {
		return amenityI18n;
	}

	public void setAmenityI18n(AmenityI18n amenityI18n) {
		this.amenityI18n = amenityI18n;
	}

    @Override
    public String toString() {
        return "Amenity{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}

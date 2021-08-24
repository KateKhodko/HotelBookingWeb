package com.khodko.RoyalHotel.model;

import java.util.ArrayList;
import java.util.List;

public class RoomType extends IdEntity {

	private Integer occupancy;
	private String image;
	private Integer size;
	private Integer price;
	private Integer rooms;
	private boolean access;
	
	private RoomTypeI18n roomTypeI18n;	
	private List<Amenity> amenities = new ArrayList<>();
		
	public RoomType() {	
		super();
	}

	public RoomType(Integer occupancy, String image, Integer size, Integer price, Integer rooms, boolean access) {		
		super();
		this.occupancy = occupancy;
		this.image = image;
		this.size = size;
		this.price = price;
		this.rooms = rooms;
		this.access = access;
	}

	public RoomType(Integer id, Integer occupancy, String image, Integer size, Integer price, Integer rooms, boolean access) {
		this(occupancy, image, size, price, rooms, access);
		this.id = id;
	}
	
	public Integer getTotalPrice() {
		int totalPrice = price;
		for (Amenity amenity : amenities) {
			totalPrice += amenity.getPrice();
		}
		return totalPrice;
	}
	
	public void addAmenity(Amenity amenity) {
		amenities.add(amenity);
	}

	public void setOccupancy(Integer occupancy) {
		this.occupancy = occupancy;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public void setRooms(Integer rooms) {
		this.rooms = rooms;
	}

	public Integer getOccupancy() {
		return occupancy;
	}

	public String getImage() {
		return image;
	}

	public Integer getSize() {
		return size;
	}

	public Integer getPrice() {
		return price;
	}

	public Integer getRooms() {
		return rooms;
	}		

	public List<Amenity> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<Amenity> amenities) {
		this.amenities = amenities;
	}
	
	public RoomTypeI18n getRoomTypeI18n() {
		return roomTypeI18n;
	}

	public void setRoomTypeI18n(RoomTypeI18n roomTypeI18n) {		
		this.roomTypeI18n = roomTypeI18n;
	}
		
	public boolean isAccess() {
		return access;
	}

	public void setAccess(boolean access) {
		this.access = access;
	}

	public String amenitiesToString() {
		StringBuilder builder = new StringBuilder("");
		String prefix = "";
		for (Amenity amenity : amenities) {
			builder.append(prefix);
			prefix = ", ";
			builder.append(amenity.getAmenityI18n().getName());			
		}
		return builder.toString();
	}
	
	@Override
	public String toString() {
		return "RoomType [id=" + id  + ", occupancy=" + occupancy
				+ ", image=" + image + ", size=" + size + ", price=" + price + ", rooms=" + rooms + "]";
	}	

}

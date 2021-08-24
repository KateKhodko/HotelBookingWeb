package com.khodko.RoyalHotel.model;

import java.util.Objects;

public class AmenityRoomType implements DbEntity {

    private Integer roomTypeId;
    private Integer amenityId;
    
    public AmenityRoomType() {
    }

    public AmenityRoomType(Integer roomTypeId, Integer amenityId) {
        this.roomTypeId = roomTypeId;
        this.amenityId = amenityId;
    }
       
    public void setRoomTypeId(Integer roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public void setAmenityId(Integer amenityId) {
		this.amenityId = amenityId;
	}

	public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public Integer getAmenityId() {
        return amenityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AmenityRoomType that = (AmenityRoomType) o;
        return Objects.equals(roomTypeId, that.roomTypeId) && Objects.equals(amenityId, that.amenityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomTypeId, amenityId);
    }

    @Override
    public String toString() {
        return "AmenityRoomType{" +
                "typeId=" + roomTypeId +
                ", amenityId=" + amenityId +
                '}';
    }
}

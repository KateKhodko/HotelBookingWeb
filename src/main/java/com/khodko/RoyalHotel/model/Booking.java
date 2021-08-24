package com.khodko.RoyalHotel.model;

import java.util.Date;
import java.util.Objects;

public class Booking extends IdEntity {

    private Integer roomTypeId;
    private Integer userId;
    private Date arrivalDate;
    private Date departDate;
    private Integer numAdults;
    private Integer numChildren;
    private Integer price;

    private RoomType roomType;
    private User user;

    public Booking() {
    	super();
    }

    public Booking(Integer roomTypeId, Integer userId, Date arrivalDate, Date departDate, Integer numAdults,
                   Integer numChildren, Integer price) {
    	super();
        this.roomTypeId = roomTypeId;
        this.userId = userId;
        this.arrivalDate = arrivalDate;
        this.departDate = departDate;
        this.numAdults = numAdults;
        this.numChildren = numChildren;
        this.price = price;
    }

    public Booking(Integer id, Integer roomTypeId, Integer userId, Date arrivalDate, Date departDate,
                   Integer numAdults, Integer numChildren, Integer price) {
        this(roomTypeId, userId, arrivalDate, departDate, numAdults, numChildren, price);
        this.id = id;
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartDate() {
        return departDate;
    }

    public void setDepartDate(Date departDate) {
        this.departDate = departDate;
    }

    public Integer getNumAdults() {
        return numAdults;
    }

    public void setNumAdults(Integer numAdults) {
        this.numAdults = numAdults;
    }

    public Integer getNumChildren() {
        return numChildren;
    }

    public void setNumChildren(Integer numChildren) {
        this.numChildren = numChildren;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) && Objects.equals(roomTypeId, booking.roomTypeId) && Objects.equals(userId, booking.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomTypeId, userId);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", roomTypeId=" + roomTypeId +
                ", userId=" + userId +
                ", arrivalDate=" + arrivalDate +
                ", departDate=" + departDate +
                ", numAdults=" + numAdults +
                ", numChildren=" + numChildren +
                ", price=" + price +
                ", roomType=" + roomType +
                ", user=" + user +
                '}';
    }
}

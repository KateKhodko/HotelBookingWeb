package com.khodko.RoyalHotel.service;

import com.khodko.RoyalHotel.dao.core.DaoHelperFactory;


public class ServiceFactory {

    private final DaoHelperFactory daoHelperFactory = new DaoHelperFactory();

    public AmenityService createAmenityService(){
        return new AmenityService(daoHelperFactory);
    }  
    
    public RoomTypeService createRoomTypeService(){
        return new RoomTypeService(daoHelperFactory);
    }
    
    public UserService createUserService(){
        return new UserService(daoHelperFactory);
    }
    
    public BookingService createBookingService(){
        return new BookingService(daoHelperFactory);
    }
    
    public I18nService createI18nService(){
        return new I18nService(daoHelperFactory);
    }
    
    public AmenityI18nService createAmenityI18nService(){
        return new AmenityI18nService(daoHelperFactory);
    }
    
    public RoomTypeI18nService createRoomTypeI18nService(){
        return new RoomTypeI18nService(daoHelperFactory);
    }
             
}

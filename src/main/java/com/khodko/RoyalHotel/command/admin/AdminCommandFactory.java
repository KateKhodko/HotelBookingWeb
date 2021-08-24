package com.khodko.RoyalHotel.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.khodko.RoyalHotel.command.BaseCommandFactory;
import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.PageCommand;
import com.khodko.RoyalHotel.config.Pages;


public class AdminCommandFactory extends BaseCommandFactory {
	
	private final static Logger LOGGER = LogManager.getLogger(AdminCommandFactory.class);
	
	public final static String SHOW_ADMIN = "main";
    public final static String SHOW_AMENITIES = "amenities";
    public final static String SHOW_AMENITY_I18N = "amenity_i18";
    public final static String SAVE_AMENITY_I18N = "save_amenity_i18";
    public final static String DELETE_AMENITY = "delete_amenity";
    public final static String SAVE_AMENITY = "save_amenity";
    public final static String SHOW_ROOMTYPE = "roomtype";
    public final static String SAVE_ROOMTYPE = "save_roomtype";
    public final static String DELETE_ROOMTYPE = "delete_roomtype";
    public final static String SHOW_ROOMTYPE_I18N = "roomtype_i18";
    public final static String SAVE_ROOMTYPE_I18N = "save_roomtype_i18";
    public final static String SHOW_ROOMTYPES = "roomtypes";
	public final static String ORDERS = "orders";
	public final static String ORDER = "order";
	public final static String DELETE_ORDER = "delete_order";
	public final static String SHOW_LANGS = "langs";
	public final static String SAVE_LANG = "save_lang";
	public final static String DELETE_LANG = "delete_lang";
	
	@Override
    public Command create(String type) throws IllegalAccessException {
        switch (type) {  
	        case SHOW_ADMIN:
	            return new PageCommand(Pages.ADMIN_MAIN); 
	            
	        case SHOW_AMENITY_I18N:
	        	return new AmenityI18nCommand(serviceFactory.createAmenityI18nService());   
	       	
	        case SAVE_AMENITY_I18N:
	        	return new SaveAmenityI18nCommand(serviceFactory.createAmenityI18nService());   
	        	
	        case SHOW_AMENITIES:
	        	return new AmenitiesCommand(serviceFactory.createAmenityService());  
	        	
	        case DELETE_AMENITY:
	        	return new DeleteAmenityCommand(serviceFactory.createAmenityService()); 	        	
	        
	        case SAVE_AMENITY:
	        	return new SaveAmenityCommand(serviceFactory.createAmenityService(), serviceFactory.createAmenityI18nService());
	        	
	        case SHOW_ROOMTYPE:
	        	return new RoomTypeCommand(serviceFactory.createRoomTypeService(), serviceFactory.createAmenityService());	  
	        	
	        case DELETE_ROOMTYPE:           	
	        	return new DeleteRoomTypeCommand(serviceFactory.createRoomTypeService(), serviceFactory.createBookingService());	
	        	
	        case SAVE_ROOMTYPE:
	        	return new SaveRoomTypeCommand(serviceFactory.createRoomTypeService(), serviceFactory.createRoomTypeI18nService(), serviceFactory.createAmenityService());
	        	
	        case SHOW_ROOMTYPES:
				return new RoomTypesCommand(serviceFactory.createRoomTypeService());
				
	        case SHOW_ROOMTYPE_I18N:
	        	return new RoomType18nCommand(serviceFactory.createRoomTypeI18nService());

			case ORDERS:
				return new OrdersCommand(serviceFactory.createBookingService());

			case ORDER:
				return new OrderCommand(serviceFactory.createBookingService());
				
			case DELETE_ORDER:
				return new DeleteOrderCommand(serviceFactory.createBookingService());
				
			case SAVE_ROOMTYPE_I18N:
				return new SaveRoomType18nCommand(serviceFactory.createRoomTypeI18nService());
				
			case SHOW_LANGS:
				return new ShowLangsCommand(serviceFactory.createI18nService());
				
			case SAVE_LANG:
	        	return new SaveLangCommand(serviceFactory.createI18nService());	
	        	
			case DELETE_LANG:
	        	return new DeleteLangCommand(serviceFactory.createI18nService());	
				       
            default:                
                return new PageCommand(Pages.ERROR_NOT_FOUND);               
        }
    }

}

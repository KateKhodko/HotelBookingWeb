package com.khodko.RoyalHotel.command.open;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.khodko.RoyalHotel.command.BaseCommandFactory;
import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.PageCommand;
import com.khodko.RoyalHotel.config.Pages;


public class PublicCommandFactory extends BaseCommandFactory {

    private final static Logger LOGGER = LogManager.getLogger(PublicCommandFactory.class);
		
	public final static String HOME = "home";
	public final static String SHOW_SIGNUP = "signup_new";
	public final static String SIGNUP = "signup_create";
	public final static String SHOW_LOGIN = "show_login";
	public final static String LOGIN = "login";
	public final static String LOGOUT = "logout";
	public final static String SHOW_ROOMTYPES = "roomtypes";

	@Override
    public Command create(String type) throws IllegalAccessException {
        switch (type) {         		     
        	case HOME:                
        		return new PageCommand(Pages.HOME);
        
        	case SHOW_SIGNUP:
        		return new ShowSignupCommand();
        		
        	case SIGNUP:
        		return new SignupCommand(serviceFactory.createUserService()); 
        		
        	case SHOW_LOGIN:
        		return new ShowLoginCommand();
        		      		
        	case LOGIN:                
        		return new LoginCommand(serviceFactory.createUserService()); 	
        		
        	case LOGOUT:                
        		return new LogoutCommand(serviceFactory.createUserService());

			case SHOW_ROOMTYPES:
				return new RoomTypesCommand(serviceFactory.createRoomTypeService());
                                        	                     	          	            
            default:                
                return new PageCommand(Pages.ERROR_NOT_FOUND);
                //throw new IllegalAccessException("Unknown type of command " + type);
        }
    }

}

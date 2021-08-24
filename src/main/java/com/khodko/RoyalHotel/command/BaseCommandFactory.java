package com.khodko.RoyalHotel.command;

import com.khodko.RoyalHotel.service.ServiceFactory;

public abstract class BaseCommandFactory {
	
	protected final ServiceFactory serviceFactory = new ServiceFactory();
    
    public abstract Command create(String type) throws IllegalAccessException;

}

package com.khodko.RoyalHotel.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.config.Path;
import com.khodko.RoyalHotel.form.FormUtil;
import com.khodko.RoyalHotel.service.BookingService;
import com.khodko.RoyalHotel.service.ServiceException;

public class DeleteOrderCommand implements Command {
	
	private final static Logger LOGGER = LogManager.getLogger(DeleteOrderCommand.class);
	
	private final BookingService bookingService;

    public DeleteOrderCommand(BookingService bookingService) {
        this.bookingService = bookingService;
    }

	@Override
	public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		int id = 0;
    	if (params != null && params.length > 0) {
        	id = FormUtil.parseUnsignedInt(params[0]);
        } 		
		if (id > 0) {
			bookingService.delete(id);			
    	}   	   	
    	return CommandResults.redirectPath(request, Path.ADMIN_ORDERS);
	}

}

package com.khodko.RoyalHotel.command.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khodko.RoyalHotel.config.Path;
import com.khodko.RoyalHotel.config.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.service.BookingService;
import com.khodko.RoyalHotel.service.RoomTypeService;
import com.khodko.RoyalHotel.service.ServiceException;
import com.khodko.RoyalHotel.form.FormUtil;


public class DeleteRoomTypeCommand implements Command {
	
	private final static Logger LOGGER = LogManager.getLogger(DeleteRoomTypeCommand.class);
			
	private final RoomTypeService roomTypeService;
	private final BookingService bookingService;
	
	public DeleteRoomTypeCommand(RoomTypeService roomTypeService, BookingService bookingService) {
        this.roomTypeService = roomTypeService;
        this.bookingService = bookingService;
    }

	@Override
	public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		int id = 0;
    	if (params != null && params.length > 0) {
        	id = FormUtil.parseUnsignedInt(params[0]);
        } 		
		if (id > 0) {
			if (bookingService.existsRoomType(id)) {
				List<String> messages = new ArrayList<>();
				messages.add("Error: Room type exists in bookings");
				request.getSession().setAttribute(Session.SESSION_MESSAGES, messages);				
			} else {
				roomTypeService.delete(id);
			}			
    	}   	   	
    	return CommandResults.redirectPath(request, Path.ADMIN_ROOMTYPES);
	}

}

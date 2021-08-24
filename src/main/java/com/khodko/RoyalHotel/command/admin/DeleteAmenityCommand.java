package com.khodko.RoyalHotel.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khodko.RoyalHotel.config.Path;
import com.khodko.RoyalHotel.config.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.service.AmenityService;
import com.khodko.RoyalHotel.service.ServiceException;
import com.khodko.RoyalHotel.form.FormUtil;

import java.util.ArrayList;
import java.util.List;


public class DeleteAmenityCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(DeleteAmenityCommand.class);

	private final AmenityService amenityService;

	public DeleteAmenityCommand(AmenityService amenityService) {
		this.amenityService = amenityService;
	}

	@Override
	public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		int id = 0;
		if (params != null && params.length > 0) {
			id = FormUtil.parseUnsignedInt(params[0]);
		}
		if (id > 0) {
			if (amenityService.existsAmenityInRoomTypes(id)) {
				List<String> messages = new ArrayList<>();
				messages.add("locale.message.amenityexists");
				request.getSession().setAttribute(Session.SESSION_MESSAGES, messages);		
			} else {
				amenityService.delete(id);
			}			
		}
		return CommandResults.redirectPath(request, Path.ADMIN_AMENITIES);
	}

}

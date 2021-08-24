package com.khodko.RoyalHotel.command.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.config.Pages;
import com.khodko.RoyalHotel.config.Session;
import com.khodko.RoyalHotel.form.AmenityForm;
import com.khodko.RoyalHotel.localization.Localization;
import com.khodko.RoyalHotel.model.Amenity;
import com.khodko.RoyalHotel.service.AmenityService;
import com.khodko.RoyalHotel.service.ServiceException;


public class AmenitiesCommand implements Command {
	
	private final static Logger LOGGER = LogManager.getLogger(AmenitiesCommand.class);
		
	private final AmenityService amenityService;
	
	public static final String AMENITIES_ATTR = "amenities";
	public static final String MESSAGES_ATTR = "messages";
    public static final String AMENITY_FORM_ATTR = "amenityForm";
	
	public AmenitiesCommand(AmenityService amenityService) {
        this.amenityService = amenityService;
    }

	@Override
	public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		setSessionData(request);
		
		int i18nId = Localization.getI18nId(request);
		List<Amenity> amenities = amenityService.findAll(i18nId);		
		request.setAttribute(AMENITIES_ATTR, amenities);
			
		return CommandResults.forward(Pages.ADMIN_AMENITIES);
	}
	
	private void setSessionData(HttpServletRequest request) {
		Object messagesObj = request.getSession().getAttribute(Session.SESSION_MESSAGES);
		if (messagesObj instanceof List<?>) {
			request.setAttribute(MESSAGES_ATTR, (List<?>) messagesObj);
			request.getSession().removeAttribute(Session.SESSION_MESSAGES);
		}
		
		Object amenityFormObj =  request.getSession().getAttribute(Session.SESSION_AMENITY_FORM);
		if (amenityFormObj instanceof AmenityForm) {
			request.setAttribute(AMENITY_FORM_ATTR, (AmenityForm) amenityFormObj);
			request.getSession().removeAttribute(Session.SESSION_AMENITY_FORM);
		}
	}

}

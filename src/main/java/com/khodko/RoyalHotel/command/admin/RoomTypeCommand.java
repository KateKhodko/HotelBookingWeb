package com.khodko.RoyalHotel.command.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khodko.RoyalHotel.form.RoomTypeForm;
import com.khodko.RoyalHotel.localization.Localization;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.config.Pages;
import com.khodko.RoyalHotel.config.Session;
import com.khodko.RoyalHotel.model.Amenity;
import com.khodko.RoyalHotel.model.RoomType;
import com.khodko.RoyalHotel.service.AmenityService;
import com.khodko.RoyalHotel.service.RoomTypeService;
import com.khodko.RoyalHotel.service.ServiceException;
import com.khodko.RoyalHotel.util.EntityCheckBox;
import com.khodko.RoyalHotel.form.FormUtil;


public class RoomTypeCommand implements Command {
	
	private final static Logger LOGGER = LogManager.getLogger(RoomTypeCommand.class);

	public static final String AMENITY_BOXES_ATTR = "amenityBoxes";
	public static final String MESSAGES_ATTR = "messages";
	public static final String ROOMTYPE_FORM_ATTR = "roomTypeForm";
				
	private final RoomTypeService roomTypeService;
	private final AmenityService amenityService;
	
	public RoomTypeCommand(RoomTypeService roomTypeService, AmenityService amenityService) {
        this.roomTypeService = roomTypeService;
        this.amenityService = amenityService;
    }

	@Override
	public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		setMessagesFromSession(request);	
		List<Amenity> amenities = setAmenitiesFromSession(request);		
		RoomTypeForm roomTypeForm = setRoomTypeFormFromSession(request);
		if (roomTypeForm == null) {			
			int id = 0;
	    	if (params != null && params.length > 0) {
	        	id = FormUtil.parseUnsignedInt(params[0]);
	        } 
			if (id > 0) {
				RoomType roomType = roomTypeService.findByIdWithAmenitiesI18n(id, Localization.DEFAULT_I18N);				
				amenities = roomType.getAmenities();
				roomTypeForm = new RoomTypeForm(
						roomType.getId(), 
						roomType.getRoomTypeI18n().getName(), 
						roomType.getOccupancy(), 
						roomType.getRoomTypeI18n().getDescription(), 
						roomType.getImage(), 
						roomType.getSize(), 
						roomType.getPrice(), 
						roomType.getRooms(),
						roomType.getRoomTypeI18n().getId(),
						roomType.isAccess());
				request.setAttribute(ROOMTYPE_FORM_ATTR, roomTypeForm);
			}
		} 

		List<Amenity> allAmenities = amenityService.findAll(Localization.DEFAULT_I18N);
		List<EntityCheckBox<Amenity>> checkBoxes = EntityCheckBox.getEntityCheckBoxes(allAmenities, amenities);
		request.setAttribute(AMENITY_BOXES_ATTR, checkBoxes);

		return CommandResults.forward(Pages.ADMIN_ADD_ROOMTYPE);
	}
	
	private void setMessagesFromSession(HttpServletRequest request) {
		Object messagesObj = request.getSession().getAttribute(Session.SESSION_MESSAGES);
		if (messagesObj instanceof List<?>) {
			request.setAttribute(MESSAGES_ATTR, (List<?>) messagesObj);
			request.getSession().removeAttribute(Session.SESSION_MESSAGES);
		}		
	}
	
	private RoomTypeForm setRoomTypeFormFromSession(HttpServletRequest request) {
		RoomTypeForm roomTypeForm = null;
		Object roomTypeFormObj = request.getSession().getAttribute(Session.SESSION_ROOMTYPE_FORM);
		if (roomTypeFormObj instanceof RoomTypeForm) {
			roomTypeForm = (RoomTypeForm) roomTypeFormObj;
			request.setAttribute(ROOMTYPE_FORM_ATTR, roomTypeForm);
			request.getSession().removeAttribute(Session.SESSION_ROOMTYPE_FORM);
		}
		return roomTypeForm;
	}
	
	private List<Amenity> setAmenitiesFromSession(HttpServletRequest request) {
		List<Amenity> amenitites = new ArrayList<>();
		Object amenititesObj = request.getSession().getAttribute(Session.SESSION_AMENITIES);
		if (amenititesObj instanceof List<?>) {
			amenitites.addAll((List<Amenity>) amenititesObj);
			request.getSession().removeAttribute(Session.SESSION_AMENITIES);
		}
		return amenitites;
	}

}

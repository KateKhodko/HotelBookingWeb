package com.khodko.RoyalHotel.command.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.config.Path;
import com.khodko.RoyalHotel.config.Session;
import com.khodko.RoyalHotel.form.RoomTypeI18nForm;
import com.khodko.RoyalHotel.model.RoomTypeI18n;
import com.khodko.RoyalHotel.service.RoomTypeI18nService;
import com.khodko.RoyalHotel.service.ServiceException;

public class SaveRoomType18nCommand implements Command {
	
	private final static Logger LOGGER = LogManager.getLogger(SaveRoomType18nCommand.class);
	
	private final RoomTypeI18nService roomTypeI18nService;

    public SaveRoomType18nCommand(RoomTypeI18nService roomTypeI18nService) {
        this.roomTypeI18nService = roomTypeI18nService;
    }

	@Override
	public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		RoomTypeI18nForm form = new RoomTypeI18nForm(request);
    	List<String> messages = form.validate();
    	if (messages.isEmpty()) {
    		RoomTypeI18n roomTypeI18n = new RoomTypeI18n(
    				form.getIdValue(), 
    				form.getRoomTypeIdValue(), 
    				form.getI18nIdValue(), 
    				form.getNameValue(),
    				form.getDescriptionValue());
    		
        	if (!roomTypeI18nService.existsByName(roomTypeI18n)) {                
        		roomTypeI18n = roomTypeI18nService.save(roomTypeI18n);  
        		form.setIdValue(roomTypeI18n.getId());
            } else {
            	messages.add("Room type exists with name.");
            }        	
    	}
    	request.getSession().setAttribute(Session.SESSION_MESSAGES, messages);
    	request.getSession().setAttribute(Session.SESSION_ROOM_TYPE_I18N_FORM, form);
    	    	
    	String[] args = {String.valueOf(form.getRoomTypeIdValue())};    	
    	return CommandResults.redirectPath(request, Path.ADMIN_ROOMTYPE_I18N, args);
	}

}

package com.khodko.RoyalHotel.command.admin;

import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.config.Pages;
import com.khodko.RoyalHotel.config.Path;
import com.khodko.RoyalHotel.config.Session;
import com.khodko.RoyalHotel.form.FormUtil;
import com.khodko.RoyalHotel.form.RoomTypeI18nForm;
import com.khodko.RoyalHotel.localization.Localization;
import com.khodko.RoyalHotel.model.RoomTypeI18n;
import com.khodko.RoyalHotel.service.RoomTypeI18nService;
import com.khodko.RoyalHotel.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RoomType18nCommand implements Command {

    private final static Logger LOGGER = LogManager.getLogger(RoomType18nCommand.class);
    
    private final static String SAVE_MESSAGE = "save_message";
    private final static String MESSAGES_ATTR = "messages";
    private final static String ROOM_TYPE_EN_ATTR = "roomTypeEn";
    private final static String ROOM_TYPE_I18N_FORM_ATTR = "roomTypeI18nForm";
    
    private final RoomTypeI18nService roomTypeI18nService;

    public RoomType18nCommand(RoomTypeI18nService roomTypeI18nService) {
        this.roomTypeI18nService = roomTypeI18nService;
    }

    @Override
    public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {          	   					   	    	
    	int roomTypeId = 0;
    	if (params != null && params.length > 0) {
    		roomTypeId = FormUtil.parseUnsignedInt(params[0]);  		   	
        } 
		if (roomTypeId > 0) {
			setMessagesFromSession(request);
			
			Optional<RoomTypeI18n> roomTypeEnOpt = roomTypeI18nService.findByRoomType(roomTypeId, Localization.DEFAULT_I18N);
			if (!roomTypeEnOpt.isPresent()) {
				return CommandResults.redirectPath(request, Path.ADMIN_ROOMTYPES);
			}
			request.setAttribute(ROOM_TYPE_EN_ATTR, roomTypeEnOpt.get());	
						
			RoomTypeI18nForm form = setRoomTypeI18nFromSession(request);
			
			if (form == null) {
				int i18nId = FormUtil.parseUnsignedInt(request.getParameter(RoomTypeI18nForm.i18nIdName));
				if (i18nId == 0) i18nId = Localization.DEFAULT_I18N;
				
				Optional<RoomTypeI18n> roomTypeI18nOpt = roomTypeI18nService.findByRoomType(roomTypeId, i18nId);
				if (roomTypeI18nOpt.isPresent()) {
					RoomTypeI18n roomTypeI18n = roomTypeI18nOpt.get();
					form = new RoomTypeI18nForm(roomTypeI18n.getId(), roomTypeI18n.getRoomTypeId(), roomTypeI18n.getI18nId(), roomTypeI18n.getName(), roomTypeI18n.getDescription());
				} else {
					form = new RoomTypeI18nForm(0, roomTypeId, i18nId, "", "");
				}
				request.setAttribute(ROOM_TYPE_I18N_FORM_ATTR, form);	
			}						
		}
    	    	
    	return CommandResults.forward(Pages.ADMIN_ROOM_TYPE_I18N);
    }
    
    private void setMessagesFromSession(HttpServletRequest request) {
		Object messagesObj = request.getSession().getAttribute(Session.SESSION_MESSAGES);
		if (messagesObj instanceof List<?>) {
			List<?> messages = (List<?>) messagesObj;
			if (messages.isEmpty()) {
				request.setAttribute(SAVE_MESSAGE, "Localization saved successfully!");
			}
			request.setAttribute(MESSAGES_ATTR, messages);
			request.getSession().removeAttribute(Session.SESSION_MESSAGES);
		}		
	}
    
    private RoomTypeI18nForm setRoomTypeI18nFromSession(HttpServletRequest request) {
		Object formObj = request.getSession().getAttribute(Session.SESSION_ROOM_TYPE_I18N_FORM);
		RoomTypeI18nForm form = null;
		if (formObj instanceof RoomTypeI18nForm) {	
			form = (RoomTypeI18nForm) formObj;
			request.setAttribute(ROOM_TYPE_I18N_FORM_ATTR, form);
			request.getSession().removeAttribute(Session.SESSION_ROOM_TYPE_I18N_FORM);									
			return form;
		}
		return null;
	}
}

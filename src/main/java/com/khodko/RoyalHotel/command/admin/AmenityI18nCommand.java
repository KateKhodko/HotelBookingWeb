package com.khodko.RoyalHotel.command.admin;

import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.config.Pages;
import com.khodko.RoyalHotel.config.Path;
import com.khodko.RoyalHotel.config.Session;
import com.khodko.RoyalHotel.form.AmenityI18nForm;
import com.khodko.RoyalHotel.form.FormUtil;
import com.khodko.RoyalHotel.localization.Localization;
import com.khodko.RoyalHotel.model.AmenityI18n;
import com.khodko.RoyalHotel.service.AmenityI18nService;
import com.khodko.RoyalHotel.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AmenityI18nCommand implements Command {

    private final static Logger LOGGER = LogManager.getLogger(AmenityI18nCommand.class);
    
    private final static String SAVE_MESSAGE = "save_message";
    private final static String MESSAGES_ATTR = "messages";
    private final static String AMENITY_EN_ATTR = "amenityEn";
    private final static String AMENITY_I18N_FORM_ATTR = "amenityI18nForm";
    
    private final AmenityI18nService amenityI18nService;

    public AmenityI18nCommand(AmenityI18nService amenityI18nService) {
        this.amenityI18nService = amenityI18nService;
    }

    @Override
    public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {          	   					   	    	
    	int amenityId = 0;
    	if (params != null && params.length > 0) {
    		amenityId = FormUtil.parseUnsignedInt(params[0]);  		   	
        } 
		if (amenityId > 0) {
			setMessagesFromSession(request);
			
			Optional<AmenityI18n> amenityEnOpt = amenityI18nService.findByAmenity(amenityId, Localization.DEFAULT_I18N);
			if (!amenityEnOpt.isPresent()) {
				return CommandResults.redirectPath(request, Path.ADMIN_AMENITIES);
			}
			request.setAttribute(AMENITY_EN_ATTR, amenityEnOpt.get());	
						
			AmenityI18nForm form = setAmenityI18nFromSession(request);
			
			if (form == null) {
				int i18nId = FormUtil.parseUnsignedInt(request.getParameter(AmenityI18nForm.i18nIdName));
				if (i18nId == 0) i18nId = Localization.DEFAULT_I18N;
				
				Optional<AmenityI18n> amenityI18nOpt = amenityI18nService.findByAmenity(amenityId, i18nId);
				if (amenityI18nOpt.isPresent()) {
					AmenityI18n amenityI18n = amenityI18nOpt.get();
					form = new AmenityI18nForm(amenityI18n.getId(), amenityI18n.getAmenityId(), amenityI18n.getI18nId(), amenityI18n.getName());
				} else {
					form = new AmenityI18nForm(0, amenityId, i18nId, "");
				}
				request.setAttribute(AMENITY_I18N_FORM_ATTR, form);	
			}						
		}
    	    	
    	return CommandResults.forward(Pages.ADMIN_AMENITY_I18N);
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
    
    private AmenityI18nForm setAmenityI18nFromSession(HttpServletRequest request) {
		Object formObj = request.getSession().getAttribute(Session.SESSION_AMENITY_I18N_FORM);
		AmenityI18nForm form = null;
		if (formObj instanceof AmenityI18nForm) {	
			form = (AmenityI18nForm) formObj;
			request.setAttribute(AMENITY_I18N_FORM_ATTR, form);
			request.getSession().removeAttribute(Session.SESSION_AMENITY_I18N_FORM);									
			return form;
		}
		return null;
	}
}

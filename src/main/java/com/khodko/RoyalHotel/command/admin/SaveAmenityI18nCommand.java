package com.khodko.RoyalHotel.command.admin;

import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.config.Path;
import com.khodko.RoyalHotel.config.Session;
import com.khodko.RoyalHotel.form.AmenityI18nForm;
import com.khodko.RoyalHotel.model.AmenityI18n;
import com.khodko.RoyalHotel.service.AmenityI18nService;
import com.khodko.RoyalHotel.service.ServiceException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveAmenityI18nCommand implements Command {
       
    private final AmenityI18nService amenityI18nService;

    public SaveAmenityI18nCommand(AmenityI18nService amenityI18nService) {
        this.amenityI18nService = amenityI18nService;
    }

    @Override
    public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {          	   			
    	AmenityI18nForm form = new AmenityI18nForm(request);
    	List<String> messages = form.validate();
    	if (messages.isEmpty()) {
    		AmenityI18n amenityI18n = new AmenityI18n(
    				form.getIdValue(), 
    				form.getAmenityIdValue(), 
    				form.getI18nIdValue(), 
    				form.getNameValue());
    		
        	if (!amenityI18nService.existsByName(amenityI18n)) {                
        		amenityI18n = amenityI18nService.save(amenityI18n);  
        		form.setIdValue(amenityI18n.getId());
            } else {
            	messages.add("locale.message.amenitywithname");
            }        	
    	}
    	request.getSession().setAttribute(Session.SESSION_MESSAGES, messages);
    	request.getSession().setAttribute(Session.SESSION_AMENITY_I18N_FORM, form);
    	    	
    	String[] args = {String.valueOf(form.getAmenityIdValue())};    	
    	return CommandResults.redirectPath(request, Path.ADMIN_AMENITY_I18N, args);
    }
}

package com.khodko.RoyalHotel.command.admin;

import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.config.Path;
import com.khodko.RoyalHotel.config.Session;
import com.khodko.RoyalHotel.form.AmenityForm;
import com.khodko.RoyalHotel.localization.Localization;
import com.khodko.RoyalHotel.model.Amenity;
import com.khodko.RoyalHotel.model.AmenityI18n;
import com.khodko.RoyalHotel.service.AmenityI18nService;
import com.khodko.RoyalHotel.service.AmenityService;
import com.khodko.RoyalHotel.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class SaveAmenityCommand implements Command {

    private final static Logger LOGGER = LogManager.getLogger(SaveAmenityCommand.class);

    private final AmenityService amenityService;
    private final AmenityI18nService amenityI18nService;

    public SaveAmenityCommand(AmenityService amenityService, AmenityI18nService amenityI18nService) {
        this.amenityService = amenityService;
        this.amenityI18nService = amenityI18nService;
    }

    @Override
    public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {            	
    	AmenityForm amenityForm = new AmenityForm(request);        
        List<String> messages = amenityForm.validate(); 
        
        AmenityI18n amenityI18n = new AmenityI18n(
    			amenityForm.getI18nIdValue(), 
    			amenityForm.getIdValue(), 
    			Localization.DEFAULT_I18N, 
    			amenityForm.getNameValue());
        
        if (amenityI18nService.existsByName(amenityI18n)) {
        	messages.add("locale.message.amenitywithname");
        }
        
        if (messages.isEmpty()) {       	      	
    		// TODO: to batch
        	Amenity amenity = new Amenity(amenityForm.getIdValue(), amenityForm.getPriceValue());
            amenity = amenityService.save(amenity);
            if (amenityForm.getIdValue() == 0) {
            	amenityI18n.setAmenityId(amenity.getId());
            	amenityI18nService.save(amenityI18n);   
            }                          
            return CommandResults.redirectPath(request, Path.ADMIN_AMENITIES);        	
        }

        request.getSession().setAttribute(Session.SESSION_MESSAGES, messages);
        if (amenityForm.getIdValue() == 0) {
            request.getSession().setAttribute(Session.SESSION_AMENITY_FORM, amenityForm);
        }
        return CommandResults.redirectPath(request, Path.ADMIN_AMENITIES);
    }
}

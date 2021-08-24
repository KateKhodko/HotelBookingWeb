package com.khodko.RoyalHotel.command.admin;

import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.config.Path;
import com.khodko.RoyalHotel.config.Session;
import com.khodko.RoyalHotel.form.LangForm;
import com.khodko.RoyalHotel.model.I18n;
import com.khodko.RoyalHotel.service.I18nService;
import com.khodko.RoyalHotel.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class SaveLangCommand implements Command {

    private final static Logger LOGGER = LogManager.getLogger(SaveLangCommand.class);

    private final I18nService i18nService;
    
    public SaveLangCommand(I18nService i18nService) {
    	this.i18nService = i18nService;
    }

    @Override
    public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {            	
    	LangForm form = new LangForm(request);        
        List<String> messages = form.validate(); 
                            
        if (messages.isEmpty()) {  
        	I18n i18n = new I18n(form.getIdValue(), form.getLocaleValue(), form.getNameValue());
	        if (!i18nService.existsByLocale(i18n)) {
	        	i18nService.save(i18n);
	        	return CommandResults.redirectPath(request, Path.ADMIN_LANGS);   	                	
	        } 
	        messages.add("Locale already exists");	            	
        }    
        
        request.getSession().setAttribute(Session.SESSION_MESSAGES, messages);
        if (form.getIdValue() == 0) {
            request.getSession().setAttribute(Session.SESSION_LANG_FORM, form);
        }
        return CommandResults.redirectPath(request, Path.ADMIN_LANGS);
    }
}

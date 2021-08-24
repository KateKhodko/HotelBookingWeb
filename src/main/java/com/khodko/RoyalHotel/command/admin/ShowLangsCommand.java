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
import com.khodko.RoyalHotel.form.LangForm;
import com.khodko.RoyalHotel.model.I18n;
import com.khodko.RoyalHotel.service.I18nService;
import com.khodko.RoyalHotel.service.ServiceException;

public class ShowLangsCommand implements Command {
	
	private final static Logger LOGGER = LogManager.getLogger(ShowLangsCommand.class);
	
	public static final String LANGS_ATTR = "langs";
	public static final String MESSAGES_ATTR = "messages";
    public static final String LANG_FORM_ATTR = "langForm";
	
	private final I18nService i18nService;
	
	public ShowLangsCommand(I18nService i18nService) {
        this.i18nService = i18nService;
    }

	@Override
	public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		setSessionData(request);
		
		List<I18n> langs = i18nService.findAll();		
		request.setAttribute(LANGS_ATTR, langs);
			
		return CommandResults.forward(Pages.ADMIN_LANGS);
	}
	
	private void setSessionData(HttpServletRequest request) {
		Object messagesObj = request.getSession().getAttribute(Session.SESSION_MESSAGES);
		if (messagesObj instanceof List<?>) {
			request.setAttribute(MESSAGES_ATTR, (List<?>) messagesObj);
			request.getSession().removeAttribute(Session.SESSION_MESSAGES);
		}
		
		Object formObj = request.getSession().getAttribute(Session.SESSION_LANG_FORM);
		if (formObj instanceof LangForm) {
			request.setAttribute(LANG_FORM_ATTR, (LangForm) formObj);
			request.getSession().removeAttribute(Session.SESSION_LANG_FORM);
		}
	}

}

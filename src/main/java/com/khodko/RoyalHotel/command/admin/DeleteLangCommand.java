package com.khodko.RoyalHotel.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khodko.RoyalHotel.config.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.service.I18nService;
import com.khodko.RoyalHotel.service.ServiceException;
import com.khodko.RoyalHotel.form.FormUtil;


public class DeleteLangCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(DeleteLangCommand.class);

	private final I18nService i18nService;

	public DeleteLangCommand(I18nService i18nService) {
		this.i18nService = i18nService;
	}

	@Override
	public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		int id = 0;
		if (params != null && params.length > 0) {
			id = FormUtil.parseUnsignedInt(params[0]);
		}
		if (id > 0) {
			i18nService.delete(id);
		}
		return CommandResults.redirectPath(request, Path.ADMIN_LANGS);
	}

}

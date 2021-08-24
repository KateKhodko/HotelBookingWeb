package com.khodko.RoyalHotel.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.khodko.RoyalHotel.service.ServiceException;


public class PageCommand implements Command {
	
	private final static Logger LOGGER = LogManager.getLogger(PageCommand.class);

    private final String page;
    
    public PageCommand(String page) {
        this.page = page;
    }

    @Override
    public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return CommandResults.forward(page);
    }
}

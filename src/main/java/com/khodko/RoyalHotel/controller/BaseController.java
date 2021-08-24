package com.khodko.RoyalHotel.controller;

import org.apache.logging.log4j.Logger;

import com.khodko.RoyalHotel.command.BaseCommandFactory;
import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.service.ServiceException;

import org.apache.logging.log4j.LogManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public abstract class BaseController extends HttpServlet {
	
	protected static final Logger LOGGER = LogManager.getLogger(BaseController.class);
	
    public static final String BASE_PATH_ATTR = "basepath";

    private final BaseCommandFactory commandFactory;
       
    public BaseController(BaseCommandFactory commandFactory) {
		super();
		this.commandFactory = commandFactory;
	}

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	process(request, response);
        } catch (IllegalAccessException e) {    
            LOGGER.fatal(e);
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	process(request, response);
        } catch (IllegalAccessException e) {
            LOGGER.fatal(e);
            e.printStackTrace();
        }
    }
       
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException {
    	String requestURI = request.getRequestURI();
    	String[] pathArray = requestURI.split("/");
    	int length = pathArray.length;
    	
    	String commandType = "";
    	String[] params = null;
    	if (length > 3) {
    		commandType = pathArray[3];
	    	if (length > 4) {
	    		params = new String[length - 4];
	    		for (int i = 4; i < length; i++) {
	    			params[i - 4] = pathArray[i];
	    		}
	    	}	    	
    	}   
    	execute(commandType, params, request, response);
    }  
       
    private void execute(String commandType, String[] params, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException {
        Command command = commandFactory.create(commandType);
        try {
            setAttributes(request);
            CommandResults view = command.execute(params, request, response);
            String page = view.getPage();
            boolean redirect = view.isRedirect();
            if (redirect) {
                response.sendRedirect(page);
            } else {
                request.getRequestDispatcher(page).forward(request, response);
            }
        } catch (ServiceException e) {
        	e.printStackTrace();
            throw new ServletException(e);
        }
    }

    private void setAttributes(HttpServletRequest request) {
        request.setAttribute(BASE_PATH_ATTR, request.getContextPath());
    }

}

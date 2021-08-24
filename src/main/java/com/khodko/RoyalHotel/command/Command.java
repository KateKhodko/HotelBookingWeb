package com.khodko.RoyalHotel.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khodko.RoyalHotel.service.ServiceException;


public interface Command {

    CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}

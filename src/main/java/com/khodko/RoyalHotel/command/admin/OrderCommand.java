package com.khodko.RoyalHotel.command.admin;

import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.config.Pages;
import com.khodko.RoyalHotel.config.Path;
import com.khodko.RoyalHotel.form.FormUtil;
import com.khodko.RoyalHotel.localization.Localization;
import com.khodko.RoyalHotel.model.Booking;
import com.khodko.RoyalHotel.service.BookingService;
import com.khodko.RoyalHotel.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class OrderCommand implements Command {

    private final static Logger LOGGER = LogManager.getLogger(OrderCommand.class);

    public static final String BOOKING_ATTR = "booking";
    
    private final BookingService bookingService;

    public OrderCommand(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {       
    	int id = 0;
    	if (params != null && params.length > 0) {
        	id = FormUtil.parseUnsignedInt(params[0]);
        }        
        if (id == 0) {
            return CommandResults.redirectPath(request, Path.ADMIN_ORDERS);
        }
        int i18nId = Localization.getI18nId(request);       
        Optional<Booking> bookingOpt = bookingService.findById(id, i18nId);
        bookingOpt.ifPresent(booking -> request.setAttribute(BOOKING_ATTR, booking));
        return CommandResults.forward(Pages.ADMIN_ORDER);
    }
}

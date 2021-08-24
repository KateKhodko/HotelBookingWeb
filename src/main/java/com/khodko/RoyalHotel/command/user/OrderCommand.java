package com.khodko.RoyalHotel.command.user;

import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.config.Pages;
import com.khodko.RoyalHotel.config.Path;
import com.khodko.RoyalHotel.form.FormUtil;
import com.khodko.RoyalHotel.localization.Localization;
import com.khodko.RoyalHotel.model.Booking;
import com.khodko.RoyalHotel.model.RoomType;
import com.khodko.RoyalHotel.service.BookingService;
import com.khodko.RoyalHotel.service.RoomTypeService;
import com.khodko.RoyalHotel.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Optional;

public class OrderCommand implements Command {

    private final static Logger LOGGER = LogManager.getLogger(OrderCommand.class);

    public static final String ROOM_TYPE_ATTR = "roomType";
    public static final String BOOKING_ATTR = "booking";
    public static final String MESSAGE_ATTR = "message";

    private final RoomTypeService roomTypeService;
    private final BookingService bookingService;

    public OrderCommand(RoomTypeService roomTypeService, BookingService bookingService) {
        this.roomTypeService = roomTypeService;
        this.bookingService = bookingService;
    }

    @Override
    public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
    	int id = 0;
    	if (params != null && params.length > 0) {
        	id = FormUtil.parseUnsignedInt(params[0]);
        } 
        Object bookingObj = request.getSession().getAttribute(BOOKING_ATTR);

        Booking booking;
        if (id > 0) {
            Optional<Booking> bookingOpt = bookingService.findById(id);
            if (!bookingOpt.isPresent()) {
                return CommandResults.redirectPath(request, Path.USER_ORDERS);
            }
            booking = bookingOpt.get();
        } else if(bookingObj != null) {
            String message = "locale.message.paymentsuccessful";
            request.setAttribute(MESSAGE_ATTR, message);
            booking = (Booking) bookingObj;
        } else {
            return CommandResults.redirectPath(request, Path.PUBLIC_HOME);
        }
        request.setAttribute(BOOKING_ATTR, booking);
        
        int i18nId = Localization.getI18nId(request);
        Optional<RoomType> roomTypeOpt = roomTypeService.findById(booking.getRoomTypeId(), i18nId);
        if (roomTypeOpt.isPresent()) {
            RoomType roomType = roomTypeOpt.get();
            request.setAttribute(ROOM_TYPE_ATTR, roomType);
        }

        return CommandResults.forward(Pages.USER_ORDER);
    }
}

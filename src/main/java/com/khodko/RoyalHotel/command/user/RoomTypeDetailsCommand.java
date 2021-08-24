package com.khodko.RoyalHotel.command.user;

import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.config.Pages;
import com.khodko.RoyalHotel.config.Path;
import com.khodko.RoyalHotel.config.Session;
import com.khodko.RoyalHotel.form.FormUtil;
import com.khodko.RoyalHotel.localization.Localization;
import com.khodko.RoyalHotel.form.BookingForm;
import com.khodko.RoyalHotel.model.RoomType;
import com.khodko.RoyalHotel.service.RoomTypeService;
import com.khodko.RoyalHotel.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.khodko.RoyalHotel.config.Session.SESSION_BOOKING;

import java.util.List;

public class RoomTypeDetailsCommand implements Command {

    private final static Logger LOGGER = LogManager.getLogger(RoomTypeDetailsCommand.class);

    public static final String ROOMTYPE_ATTR = "roomType";
    public static final String TOTAL_PRICE_ATTR = "totalPrice";
    public static final String BOOKING_FORM_ATTR = "bookingForm";
    public static final String MESSAGES_ATTR = "messages";

    private final RoomTypeService roomTypeService;

    public RoomTypeDetailsCommand(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    @Override
    public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
    	int id = 0;
    	if (params != null && params.length > 0) {
        	id = FormUtil.parseUnsignedInt(params[0]);
        }    	
        RoomType roomType = null;
        if (id > 0) {
        	int i18nId = Localization.getI18nId(request);
            roomType = roomTypeService.findByIdWithAmenitiesI18n(id, i18nId);           
        }
        if (roomType == null) {
            return CommandResults.redirectPath(request, Path.PUBLIC_ROOMTYPES);
        }
        int totalPrice = roomTypeService.countTotalPrice(roomType);
        request.setAttribute(TOTAL_PRICE_ATTR, totalPrice);
        
        BookingForm form = (BookingForm) request.getSession().getAttribute(SESSION_BOOKING);
        if (form != null && form.getIdValue() == roomType.getId()) {
            request.setAttribute(BOOKING_FORM_ATTR, form);
        }
        request.setAttribute(ROOMTYPE_ATTR, roomType);
        setMessagesFromSession(request);
        return CommandResults.forward(Pages.USER_ROOMTYPE_DETAILS);
    }
    
    private void setMessagesFromSession(HttpServletRequest request) {
		Object messagesObj = request.getSession().getAttribute(Session.SESSION_MESSAGES);
		if (messagesObj instanceof List<?>) {
			request.setAttribute(MESSAGES_ATTR, (List<?>) messagesObj);
			request.getSession().removeAttribute(Session.SESSION_MESSAGES);
		}		
	}
}

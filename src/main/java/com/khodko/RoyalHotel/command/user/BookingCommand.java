package com.khodko.RoyalHotel.command.user;

import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.config.Path;
import com.khodko.RoyalHotel.config.Session;
import com.khodko.RoyalHotel.form.BookingForm;
import com.khodko.RoyalHotel.model.RoomType;
import com.khodko.RoyalHotel.service.BookingService;
import com.khodko.RoyalHotel.service.RoomTypeService;
import com.khodko.RoyalHotel.service.ServiceException;
import com.khodko.RoyalHotel.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.khodko.RoyalHotel.config.Session.SESSION_BOOKING;

public class BookingCommand implements Command {

    private final static Logger LOGGER = LogManager.getLogger(BookingCommand.class);

    private final RoomTypeService roomTypeService;
    private final BookingService bookingService;

    public BookingCommand(RoomTypeService roomTypeService, BookingService bookingService) {
        this.roomTypeService = roomTypeService;
        this.bookingService = bookingService;
    }

    @Override
    public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        BookingForm form = new BookingForm(request);
        List<String> messages = form.validate();
        request.getSession().setAttribute(SESSION_BOOKING, form);
        if (messages.isEmpty()) {
        	
        	Optional<RoomType> roomTypeOpt =  roomTypeService.findById(form.getIdValue());
        	if (!roomTypeOpt.isPresent()) {
                return CommandResults.redirectPath(request, Path.PUBLIC_ROOMTYPES);
            }
            
        	RoomType roomType = roomTypeOpt.get();
            if (form.getAdultsValue() + form.getChildrenValue() > roomType.getOccupancy()) {
                messages.add("locale.message.manypeople");
            } else {
                Date arrival = form.getDate();
                Date depart = DateUtil.plusDays(arrival, form.getDaysValue());
                if (bookingService.findByDates(arrival, depart).size() >= roomType.getRooms()) {
                    messages.add("locale.message.norooms");
                } else {
                    return CommandResults.redirectPath(request, Path.USER_PAYMENT);
                }
            }
        }        
        request.getSession().setAttribute(Session.SESSION_MESSAGES, messages);
        String[] args = new String[] {String.valueOf(form.getIdValue())};
        return CommandResults.redirectPath(request, Path.USER_ROOMTYPE_DETAILS, args);
    }
}

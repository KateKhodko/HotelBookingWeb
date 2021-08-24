package com.khodko.RoyalHotel.command.user;

import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.config.Path;
import com.khodko.RoyalHotel.config.Session;
import com.khodko.RoyalHotel.form.BookingForm;
import com.khodko.RoyalHotel.form.PaymentForm;
import com.khodko.RoyalHotel.localization.Localization;
import com.khodko.RoyalHotel.model.Booking;
import com.khodko.RoyalHotel.model.RoomType;
import com.khodko.RoyalHotel.model.User;
import com.khodko.RoyalHotel.service.BookingService;
import com.khodko.RoyalHotel.service.RoomTypeService;
import com.khodko.RoyalHotel.service.ServiceException;
import com.khodko.RoyalHotel.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static com.khodko.RoyalHotel.config.Config.USER_REQUEST_ATTR;
import static com.khodko.RoyalHotel.config.Session.SESSION_BOOKING;

public class ConfirmPaymentCommand implements Command {

    private final static Logger LOGGER = LogManager.getLogger(ConfirmPaymentCommand.class);

    public static final String BOOKING_ATTR = "booking";

    private final RoomTypeService roomTypeService;
    private final UserService userService;
    private final BookingService bookingService;

    public ConfirmPaymentCommand(RoomTypeService roomTypeService, UserService userService, BookingService bookingService) {
        this.roomTypeService = roomTypeService;
        this.userService = userService;
        this.bookingService = bookingService;
    }

    @Override
    public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        BookingForm bookingForm = (BookingForm) request.getSession().getAttribute(SESSION_BOOKING);
        if (bookingForm == null) {
            return CommandResults.redirectPath(request, Path.PUBLIC_ROOMTYPES);
        }

        PaymentForm paymentForm = new PaymentForm(request);
        List<String> messages = paymentForm.validate();

        if (messages.isEmpty()) {
            User user = (User) request.getAttribute(USER_REQUEST_ATTR);
            if (user == null || user.getId() != paymentForm.getUserIdValue()) {
                return CommandResults.redirectPath(request, Path.PUBLIC_SHOW_LOGIN);
            } 
            
            Optional<RoomType> roomTypeOpt =  roomTypeService.findById(bookingForm.getIdValue());
            if (!roomTypeOpt.isPresent()) {
                return CommandResults.redirectPath(request, Path.PUBLIC_ROOMTYPES);
            }
            
            userService.save(paymentForm, user);
            int totalPrice = roomTypeService.countTotalPrice(roomTypeOpt.get());
            Booking booking = bookingService.save(paymentForm, bookingForm, totalPrice);
            request.getSession().setAttribute(BOOKING_ATTR, booking);
            
            return CommandResults.redirectPath(request, Path.USER_ORDER);
        }

        request.getSession().setAttribute(Session.SESSION_MESSAGES, messages);
        request.getSession().setAttribute(Session.SESSION_PAYMENT_FORM, paymentForm);
        return CommandResults.redirectPath(request, Path.USER_PAYMENT);
    }
}

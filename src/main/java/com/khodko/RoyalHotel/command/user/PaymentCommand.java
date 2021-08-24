package com.khodko.RoyalHotel.command.user;

import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.config.Pages;
import com.khodko.RoyalHotel.config.Path;
import com.khodko.RoyalHotel.config.Session;
import com.khodko.RoyalHotel.form.BookingForm;
import com.khodko.RoyalHotel.form.PaymentForm;
import com.khodko.RoyalHotel.localization.Localization;
import com.khodko.RoyalHotel.model.RoomType;
import com.khodko.RoyalHotel.model.User;
import com.khodko.RoyalHotel.service.RoomTypeService;
import com.khodko.RoyalHotel.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static com.khodko.RoyalHotel.config.Config.USER_REQUEST_ATTR;
import static com.khodko.RoyalHotel.config.Session.SESSION_BOOKING;

public class PaymentCommand implements Command {

    private final static Logger LOGGER = LogManager.getLogger(BookingCommand.class);

    public static final String YEAR_NOW_ATTR = "yearNow";
    public static final String ROOM_TYPE_ATTR = "roomType";
    public static final String TOTAL_PRICE_ATTR = "totalPrice";
    public static final String PAYMENT_FORM_ATTR = "paymentForm";
    public static final String MESSAGES_ATTR = "messages";

    private final RoomTypeService roomTypeService;

    public PaymentCommand(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    @Override
    public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        request.setAttribute(YEAR_NOW_ATTR, year);

        setMessagesFromSession(request);
        PaymentForm paymentForm = setPaymentFormFromSession(request);

        if (paymentForm == null) {
            User user = (User) request.getAttribute(USER_REQUEST_ATTR);
            if (user == null) {
                return CommandResults.redirectPath(request, Path.PUBLIC_LOGIN);
            }
            paymentForm = new PaymentForm(user.getId(), user.getFirstName(), user.getLastName(), user.getCountry(), user.getCardType(), user.getCardNumber());
            request.setAttribute(PAYMENT_FORM_ATTR, paymentForm);
        }

        BookingForm form = (BookingForm) request.getSession().getAttribute(SESSION_BOOKING);
        if (form == null || form.getIdValue() == 0) {
            return CommandResults.redirectPath(request, Path.PUBLIC_ROOMTYPES);
        }
        
        int i18nId = Localization.getI18nId(request);
        Optional<RoomType> roomTypeOpt = roomTypeService.findById(form.getIdValue(), i18nId);
        if (!roomTypeOpt.isPresent()) {
        	return CommandResults.redirectPath(request, Path.PUBLIC_ROOMTYPES);
        }
        
    	RoomType roomType = roomTypeOpt.get();
    	int totalPrice = roomTypeService.countTotalPrice(roomType);
        request.setAttribute(TOTAL_PRICE_ATTR, totalPrice);
        request.setAttribute(ROOM_TYPE_ATTR, roomType);

        return CommandResults.forward(Pages.USER_PAYMENT);
    }

    private void setMessagesFromSession(HttpServletRequest request) {
        Object messagesObj = request.getSession().getAttribute(Session.SESSION_MESSAGES);
        if (messagesObj instanceof List<?>) {
            request.setAttribute(MESSAGES_ATTR, messagesObj);
            request.getSession().removeAttribute(Session.SESSION_MESSAGES);
        }
    }

    private PaymentForm setPaymentFormFromSession(HttpServletRequest request) {
        PaymentForm paymentForm = null;
        Object paymentFormObj = request.getSession().getAttribute(Session.SESSION_ROOMTYPE_FORM);
        if (paymentFormObj instanceof PaymentForm) {
            paymentForm = (PaymentForm) paymentFormObj;
            request.setAttribute(PAYMENT_FORM_ATTR, paymentForm);
            request.getSession().removeAttribute(Session.SESSION_ROOMTYPE_FORM);
        }
        return paymentForm;
    }
}

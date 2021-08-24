package com.khodko.RoyalHotel.command.user;

import com.khodko.RoyalHotel.command.BaseCommandFactory;
import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.PageCommand;
import com.khodko.RoyalHotel.config.Pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserCommandFactory extends BaseCommandFactory {

    private final static Logger LOGGER = LogManager.getLogger(UserCommandFactory.class);

    public final static String PROFILE = "profile";
    public final static String SAVE_PROFILE = "save_profile";
    public final static String ROOMTYPE_DETAILS = "roomtype_details";
    public final static String BOOKING = "booking";
    public final static String PAYMENT = "payment";
    public final static String CONFIRM_PAYMENT = "confirm_payment";
    public final static String ORDER = "order";
    public final static String ORDERS = "orders";

    @Override
    public Command create(String type) throws IllegalAccessException {
        switch (type) {

            case PROFILE:
                return new ProfileCommand();

            case SAVE_PROFILE:
                return new SaveProfileCommand(serviceFactory.createUserService());

            case ROOMTYPE_DETAILS:
                return new RoomTypeDetailsCommand(serviceFactory.createRoomTypeService());

            case BOOKING:
                return new BookingCommand(serviceFactory.createRoomTypeService(), serviceFactory.createBookingService());

            case PAYMENT:
                return new PaymentCommand(serviceFactory.createRoomTypeService());

            case CONFIRM_PAYMENT:
                return new ConfirmPaymentCommand(serviceFactory.createRoomTypeService(), serviceFactory.createUserService(), serviceFactory.createBookingService());

            case ORDER:
                return new OrderCommand(serviceFactory.createRoomTypeService(), serviceFactory.createBookingService());

            case ORDERS:
                return new OrdersCommand(serviceFactory.createBookingService());

            default:
                return new PageCommand(Pages.ERROR_NOT_FOUND);
            //throw new IllegalAccessException("Unknown type of command " + type);
        }
    }

}

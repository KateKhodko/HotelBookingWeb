package com.khodko.RoyalHotel.command.admin;

import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.config.Path;
import com.khodko.RoyalHotel.config.Session;
import com.khodko.RoyalHotel.form.AmenityCheckBoxForm;
import com.khodko.RoyalHotel.form.RoomTypeForm;
import com.khodko.RoyalHotel.localization.Localization;
import com.khodko.RoyalHotel.model.Amenity;
import com.khodko.RoyalHotel.model.RoomType;
import com.khodko.RoyalHotel.model.RoomTypeI18n;
import com.khodko.RoyalHotel.service.AmenityService;
import com.khodko.RoyalHotel.service.RoomTypeI18nService;
import com.khodko.RoyalHotel.service.RoomTypeService;
import com.khodko.RoyalHotel.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class SaveRoomTypeCommand implements Command {

    private final static Logger LOGGER = LogManager.getLogger(SaveRoomTypeCommand.class);

    private final RoomTypeService roomTypeService;
    private final RoomTypeI18nService roomTypeI18nService;
    private final AmenityService amenityService;

    public SaveRoomTypeCommand(RoomTypeService roomTypeService, RoomTypeI18nService roomTypeI18nService, AmenityService amenityService) {
        this.roomTypeService = roomTypeService;
        this.roomTypeI18nService = roomTypeI18nService;
        this.amenityService = amenityService;        
    }

    @Override
    public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        RoomTypeForm roomTypeForm = new RoomTypeForm(request);
        List<String> messages = roomTypeForm.validate();
        
        RoomTypeI18n roomTypeI18n = new RoomTypeI18n(
        		roomTypeForm.getRoomTypeI18nIdValue(),
        		roomTypeForm.getIdValue(), 
        		Localization.DEFAULT_I18N, 
        		roomTypeForm.getNameValue(), 
        		roomTypeForm.getDescriptionValue());

        if (roomTypeI18nService.existsByName(roomTypeI18n)) {
            messages.add("locale.message.roomtypewithname");
        }

        AmenityCheckBoxForm amenityCheckBoxForm = new AmenityCheckBoxForm(request);
        List<String> checkBoxMessages = amenityCheckBoxForm.validate();
        messages.addAll(checkBoxMessages);

        List<Amenity> checkBoxAmenities = new ArrayList<>();
        if (checkBoxMessages.isEmpty()) {
            checkBoxAmenities.addAll(getAmenities(amenityCheckBoxForm.getIdValues()));
        }

        if (messages.isEmpty()) {        	
        	// TODO: to batch
            RoomType roomType = roomTypeService.save(roomTypeForm, checkBoxAmenities);
            roomTypeI18n.setRoomTypeId(roomType.getId());
            roomTypeI18nService.save(roomTypeI18n);                                       
            return CommandResults.redirectPath(request, Path.ADMIN_ROOMTYPES);
        }

        request.getSession().setAttribute(Session.SESSION_MESSAGES, messages);
        request.getSession().setAttribute(Session.SESSION_ROOMTYPE_FORM, roomTypeForm);
        request.getSession().setAttribute(Session.SESSION_AMENITIES, checkBoxAmenities);

        return CommandResults.redirectPath(request, Path.ADMIN_ROOMTYPE);
    }

    private List<Amenity> getAmenities(int[] ids) throws ServiceException {
        List<Amenity> checkBoxAmenities = new ArrayList<>();
        if (ids != null) {
            List<Amenity> amenities = amenityService.findAll(Localization.DEFAULT_I18N);
            for (Amenity amenity : amenities) {
                for (int id : ids) {
                    if (amenity.getId() == id) {
                        checkBoxAmenities.add(amenity);
                    }
                }
            }
        }
        return checkBoxAmenities;
    }

}

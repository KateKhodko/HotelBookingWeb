package com.khodko.RoyalHotel.command.open;

import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.config.Pages;
import com.khodko.RoyalHotel.config.Session;
import com.khodko.RoyalHotel.dao.querydsl.OrderBy;
import com.khodko.RoyalHotel.form.OrderByForm;
import com.khodko.RoyalHotel.form.OrdersFilterForm;
import com.khodko.RoyalHotel.form.RoomTypeFilterForm;
import com.khodko.RoyalHotel.localization.Localization;
import com.khodko.RoyalHotel.model.RoomType;
import com.khodko.RoyalHotel.service.RoomTypeService;
import com.khodko.RoyalHotel.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class RoomTypesCommand implements Command {
	
	private final static Logger LOGGER = LogManager.getLogger(RoomTypesCommand.class);
	
	public static final String ROOMTYPES_ATTR = "roomTypes";
	
	private final RoomTypeService roomTypeService;
	
	public RoomTypesCommand(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

	@Override
	public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		handleFilterForm(request);
		RoomTypeFilterForm filterForm = (RoomTypeFilterForm) request.getSession().getAttribute(Session.SESSION_ROOMTYPE_FILTER_FORM);	
		if (filterForm == null) filterForm = new RoomTypeFilterForm(request);
		
		handleOrderByForm(request);
    	OrderByForm orderByForm = (OrderByForm) request.getSession().getAttribute(Session.SESSION_ROOMTYPES_ORDER_BY);
    	OrderBy orderBy = roomTypeService.orderBy(orderByForm);
		
		int i18nId = Localization.getI18nId(request);
		List<RoomType> roomTypes = roomTypeService.findAll(i18nId, filterForm.getArrivalDate(), filterForm.getDepartDate(), orderBy, true);
		request.setAttribute(ROOMTYPES_ATTR, roomTypes);		
		return CommandResults.forward(Pages.ROOMTYPES);
	}
	
	private void handleFilterForm(HttpServletRequest request) {
    	RoomTypeFilterForm filterForm = new RoomTypeFilterForm(request);
    	if (filterForm.isFilter()) {
    		request.getSession().setAttribute(Session.SESSION_ROOMTYPE_FILTER_FORM, filterForm);
    	} else if (filterForm.isReset()) {
    		request.getSession().removeAttribute(Session.SESSION_ROOMTYPE_FILTER_FORM);
    	}   	   	
    }
	
	private void handleOrderByForm(HttpServletRequest request) {
    	OrderByForm orderByForm = new OrderByForm(request);
    	if (!orderByForm.getColumnValue().isEmpty()) {
    		OrderByForm formFromSession = (OrderByForm) request.getSession().getAttribute(Session.SESSION_ROOMTYPES_ORDER_BY);
    		if (formFromSession != null && formFromSession.getColumnValue().equals(orderByForm.getColumnValue())) {
    			orderByForm.setAsc(!formFromSession.isAsc());
    		}    		
    		request.getSession().setAttribute(Session.SESSION_ROOMTYPES_ORDER_BY, orderByForm);
    	}  
    }

}

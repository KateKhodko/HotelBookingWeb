package com.khodko.RoyalHotel.command.user;

import com.khodko.RoyalHotel.command.Command;
import com.khodko.RoyalHotel.command.CommandResults;
import com.khodko.RoyalHotel.config.Config;
import com.khodko.RoyalHotel.config.Pages;
import com.khodko.RoyalHotel.config.Path;
import com.khodko.RoyalHotel.config.Session;
import com.khodko.RoyalHotel.dao.querydsl.OrderBy;
import com.khodko.RoyalHotel.dao.querydsl.where.BaseWhere;
import com.khodko.RoyalHotel.form.OrderByForm;
import com.khodko.RoyalHotel.form.OrdersFilterForm;
import com.khodko.RoyalHotel.localization.Localization;
import com.khodko.RoyalHotel.model.Booking;
import com.khodko.RoyalHotel.model.User;
import com.khodko.RoyalHotel.pagination.PaginationHandler;
import com.khodko.RoyalHotel.service.BookingService;
import com.khodko.RoyalHotel.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OrdersCommand implements Command {

    public static final String ORDERS_ATTR = "orders";

    public static final int[] ITEMS_COUNT = {5, 10, 20};

    private final BookingService bookingService;

    public OrdersCommand(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public CommandResults execute(String[] params, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        User user = (User) request.getAttribute(Config.USER_REQUEST_ATTR);
        if (user == null) {
            return CommandResults.redirectPath(request, Path.PUBLIC_LOGIN);
        }
        
        handleOrderByForm(request);
    	OrderByForm orderByForm = (OrderByForm) request.getSession().getAttribute(Session.SESSION_USER_ORDERS_ORDER_BY);
    	OrderBy orderBy = bookingService.orderBy(orderByForm);
    	
    	handleFilterForm(request);    	
    	OrdersFilterForm filterForm = (OrdersFilterForm) request.getSession().getAttribute(Session.SESSION_USER_ORDERS_FILTER_FORM);   	
    	BaseWhere where = bookingService.filter(filterForm); 

        int count = bookingService.countByUser(user.getId(), where);
        String paginationUrl = request.getContextPath() + Path.USER_ORDERS;
        PaginationHandler paginationHandler = PaginationHandler.handle(ITEMS_COUNT, count, paginationUrl, request);

        int i18nId = Localization.getI18nId(request);
        List<Booking> orders = bookingService.findAllByUserId(i18nId, user.getId(), paginationHandler.getLimit(), paginationHandler.getOffset(), where, orderBy);
        
        request.setAttribute(ORDERS_ATTR, orders);

        return CommandResults.forward(Pages.USER_ORDERS);
    }
    
    private void handleFilterForm(HttpServletRequest request) {
    	OrdersFilterForm filterForm = new OrdersFilterForm(request);
    	if (filterForm.isFilter()) {
    		request.getSession().setAttribute(Session.SESSION_USER_ORDERS_FILTER_FORM, filterForm);
    	} else if (filterForm.isReset()) {
    		request.getSession().removeAttribute(Session.SESSION_USER_ORDERS_FILTER_FORM);
    	}   	   	
    }
    
    private void handleOrderByForm(HttpServletRequest request) {
    	OrderByForm orderByForm = new OrderByForm(request);
    	if (!orderByForm.getColumnValue().isEmpty()) {
    		OrderByForm formFromSession = (OrderByForm) request.getSession().getAttribute(Session.SESSION_USER_ORDERS_ORDER_BY);
    		if (formFromSession != null && formFromSession.getColumnValue().equals(orderByForm.getColumnValue())) {
    			orderByForm.setAsc(!formFromSession.isAsc());
    		}    		
    		request.getSession().setAttribute(Session.SESSION_USER_ORDERS_ORDER_BY, orderByForm);
    	}  
    }
}

package com.khodko.RoyalHotel.service;

import com.khodko.RoyalHotel.dao.BookingDaoApi;
import com.khodko.RoyalHotel.dao.DaoHelper;
import com.khodko.RoyalHotel.dao.core.DaoHelperFactory;
import com.khodko.RoyalHotel.dao.querydsl.OrderBy;
import com.khodko.RoyalHotel.dao.querydsl.where.BaseWhere;
import com.khodko.RoyalHotel.dao.schema.BookingTable;
import com.khodko.RoyalHotel.form.BookingForm;
import com.khodko.RoyalHotel.form.OrderByForm;
import com.khodko.RoyalHotel.form.OrdersFilterForm;
import com.khodko.RoyalHotel.form.PaymentForm;
import com.khodko.RoyalHotel.model.Booking;
import com.khodko.RoyalHotel.util.DateUtil;
import static com.khodko.RoyalHotel.dao.querydsl.where.Where.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public class BookingService {
	
    public static final String ARRIVAL_DATE_COLUMN = "arrivalDate";
    public static final String DEPART_DATE_COLUMN = "departDate";

    private final DaoHelperFactory daoHelperFactory;
       
    public BookingService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }
    
    public BaseWhere filter(OrdersFilterForm filterForm) {
    	BaseWhere where = null;
    	if (filterForm != null) {

    		Optional<Date> arrivalDateBeginOpt = filterForm.getArrivalDateBegin();
    		if (arrivalDateBeginOpt.isPresent()) {
    			where = ge(BookingTable.ARRIVAL_DATE, arrivalDateBeginOpt.get());
    		}
    		
    		Optional<Date> arrivalDateEndOpt = filterForm.getArrivalDateEnd();
    		if (arrivalDateEndOpt.isPresent()) {
    			where = and(where, le(BookingTable.ARRIVAL_DATE, arrivalDateEndOpt.get()));
    		}
    		
    		Optional<Date> departDateBeginOpt = filterForm.getDepartDateBegin();
    		if (departDateBeginOpt.isPresent()) {
    			where = and(where, ge(BookingTable.DEPART_DATE, departDateBeginOpt.get()));
    		}
    		
    		Optional<Date> departDateEndOpt = filterForm.getDepartDateEnd();
    		if (departDateEndOpt.isPresent()) {
    			where = and(where, le(BookingTable.DEPART_DATE, departDateEndOpt.get()));    			
    		}   		
    	}    	
    	return where;    	   	
    }
    

    public OrderBy orderBy(OrderByForm orderByForm) {
    	if (orderByForm != null && !orderByForm.getColumnValue().isEmpty()) {
    		switch (orderByForm.getColumnValue()) {
    			case ARRIVAL_DATE_COLUMN: 
    				return new OrderBy(orderByForm.isAsc(), BookingTable.ARRIVAL_DATE);
    			case DEPART_DATE_COLUMN:
    				return new OrderBy(orderByForm.isAsc(), BookingTable.DEPART_DATE);
    		}
    	} 
    	return new OrderBy();
    }
    
    public void delete(int id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
        	BookingDaoApi dao = daoHelper.createBookingDao();
            dao.delete(id);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
    
    public boolean existsRoomType(int roomTypeId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
        	BookingDaoApi dao = daoHelper.createBookingDao();
            return dao.existsRoomType(roomTypeId);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
   
    public int countByUser(int id, BaseWhere where) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            BookingDaoApi dao = daoHelper.createBookingDao();
            return dao.countByUserId(id, where);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
    
    public int countAll(BaseWhere where) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            BookingDaoApi dao = daoHelper.createBookingDao();
            return dao.countAll(where);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
    
    public Optional<Booking> findById(int id, int I18nId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            BookingDaoApi dao = daoHelper.createBookingDao();
            return dao.findById(id, I18nId);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
    
    public List<Booking> findAllByUserId(int i18nId, int userId, int limit, int offset, BaseWhere where, OrderBy orderBy) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            BookingDaoApi dao = daoHelper.createBookingDao();
            return dao.findAllByUserId(i18nId, userId, limit, offset, where, orderBy);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
    
    public List<Booking> findAll(int i18nId, int limit, int offset, BaseWhere where, OrderBy orderBy) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {       	
            BookingDaoApi dao = daoHelper.createBookingDao();            
            return dao.findAll(i18nId, limit, offset, where, orderBy);                                              
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public Booking save(PaymentForm paymentForm, BookingForm bookingForm, int totalPrice) throws ServiceException {
        int days = bookingForm.getDaysValue();
        Date arrivalDate = bookingForm.getDate();
        Date departDate = DateUtil.plusDays(arrivalDate, days);
        Booking booking = new Booking(bookingForm.getIdValue(), paymentForm.getUserIdValue(), arrivalDate, departDate, bookingForm.getAdultsValue(), bookingForm.getChildrenValue(), totalPrice);
        return save(booking);
    }

    public Booking save(Booking booking) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            BookingDaoApi dao = daoHelper.createBookingDao();
            return (booking.getId() > 0) ? dao.update(booking) : dao.save(booking);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Booking> findById(int id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            BookingDaoApi dao = daoHelper.createBookingDao();
            return dao.findById(id);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public List<Booking> findByDates(Date arrival, Date depart) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            BookingDaoApi dao = daoHelper.createBookingDao();
            return dao.findByDate(new java.sql.Date(arrival.getTime()), new java.sql.Date(depart.getTime()));
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

}

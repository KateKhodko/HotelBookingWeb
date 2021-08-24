package com.khodko.RoyalHotel.service;

import com.khodko.RoyalHotel.dao.AmenityDaoApi;
import com.khodko.RoyalHotel.dao.AmenityRoomTypeDaoApi;
import com.khodko.RoyalHotel.dao.DaoHelper;
import com.khodko.RoyalHotel.dao.RoomTypeDaoApi;
import com.khodko.RoyalHotel.dao.core.DaoHelperFactory;
import com.khodko.RoyalHotel.dao.querydsl.OrderBy;
import com.khodko.RoyalHotel.dao.schema.RoomTypeTable;
import com.khodko.RoyalHotel.form.OrderByForm;
import com.khodko.RoyalHotel.form.RoomTypeForm;
import com.khodko.RoyalHotel.model.Amenity;
import com.khodko.RoyalHotel.model.AmenityRoomType;
import com.khodko.RoyalHotel.model.RoomType;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class RoomTypeService {
	
	public static final String OCCUPANCY_COLUMN = "occupancy";
    public static final String SIZE_COLUMN = "size";
    public static final String ROOMS_COLUMN = "rooms";
    public static final String BASEPRICE_COLUMN = "baseprice";

    private final DaoHelperFactory daoHelperFactory;

    public RoomTypeService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }
    
    public OrderBy orderBy(OrderByForm orderByForm) {
    	if (orderByForm != null && !orderByForm.getColumnValue().isEmpty()) {
    		switch (orderByForm.getColumnValue()) {
    			case OCCUPANCY_COLUMN: 
    				return new OrderBy(orderByForm.isAsc(), RoomTypeTable.OCCUPANCY);
    			case SIZE_COLUMN:
    				return new OrderBy(orderByForm.isAsc(), RoomTypeTable.SIZE);
    			case ROOMS_COLUMN:
    				return new OrderBy(orderByForm.isAsc(), RoomTypeTable.ROOMS);
    			case BASEPRICE_COLUMN:
    				return new OrderBy(orderByForm.isAsc(), RoomTypeTable.PRICE);
    		}
    	} 
    	return new OrderBy();
    }
    
    public List<RoomType> findAll(int i18nId, Optional<Date> arrivalDateOpt, Optional<Date> departDateOpt, OrderBy orderBy, boolean onlyAccesible) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            RoomTypeDaoApi dao = daoHelper.createRoomTypeDao();   
            List<RoomType> roomTypes = dao.findAll(i18nId, arrivalDateOpt, departDateOpt, orderBy, onlyAccesible);
            for (RoomType roomType : roomTypes) {
            	AmenityDaoApi amenityDaoApi = daoHelper.createAmenityDao();
            	List<Amenity> amenities = amenityDaoApi.findAllByRoomType(roomType.getId(), i18nId);
            	roomType.setAmenities(amenities);
            }           
            return roomTypes;
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
       
    public void delete(int id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            RoomTypeDaoApi dao = daoHelper.createRoomTypeDao();
            dao.delete(id);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<RoomType> findById(int id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            RoomTypeDaoApi dao = daoHelper.createRoomTypeDao();
            return dao.findById(id);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
    
    public Optional<RoomType> findById(int id, int i18nId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            RoomTypeDaoApi dao = daoHelper.createRoomTypeDao();
            return dao.findById(id, i18nId);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public RoomType save(RoomTypeForm form, List<Amenity> amenities) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {

            RoomTypeDaoApi roomTypeDao = daoHelper.createRoomTypeDao();
            AmenityRoomTypeDaoApi amenityRoomTypeDao = daoHelper.createAmenityRoomTypeDao();

            RoomType roomType = new RoomType(
            		form.getIdValue(), 
            		form.getOccupancyValue(), 
            		form.getImageValue(), 
            		form.getSizeValue(), 
            		form.getPriceValue(), 
            		form.getRoomsValue(),
            		form.isAccessValue());
            roomType.setAmenities(amenities);

            if (roomType.getId() > 0) {
                roomTypeDao.update(roomType);
                amenityRoomTypeDao.deleteByRoomType(roomType.getId());
            } else {
                RoomType roomTypeSaved = roomTypeDao.save(roomType);
                roomType.setId(roomTypeSaved.getId());
            }
            for (Amenity amenity : roomType.getAmenities()) {
                AmenityRoomType amenityRoomType = new AmenityRoomType(roomType.getId(), amenity.getId());
                amenityRoomTypeDao.save(amenityRoomType);
            }
            return roomType;
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
    
    public int countTotalPrice(RoomType roomType) throws ServiceException {
    	try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            AmenityDaoApi amenityDao = daoHelper.createAmenityDao();
            List<Amenity> amenities = amenityDao.findByRoomType(roomType.getId());
            int totalPrice = roomType.getPrice();
    		for (Amenity amenity : amenities) {
    			totalPrice += amenity.getPrice();
    		}
    		return totalPrice;                        
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
    
    public RoomType findByIdWithAmenitiesI18n(int id, int i18nId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.createDao()) {
            RoomTypeDaoApi roomTypeDao = daoHelper.createRoomTypeDao();
            AmenityDaoApi amenityDao = daoHelper.createAmenityDao();
            Optional<RoomType> roomTypeOptional = roomTypeDao.findById(id, i18nId);
            if (roomTypeOptional.isPresent()) {
                RoomType roomType = roomTypeOptional.get();
                List<Amenity> amenities = amenityDao.findAllByRoomType(roomType.getId(), i18nId);
                roomType.setAmenities(amenities);
                return roomType;
            }
            return null;
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
    
}

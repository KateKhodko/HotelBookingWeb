package com.khodko.RoyalHotel.dao.impl;

import com.khodko.RoyalHotel.connection.ProxyConnection;
import com.khodko.RoyalHotel.dao.BookingDaoApi;
import com.khodko.RoyalHotel.dao.mapper.BookingMapper;
import com.khodko.RoyalHotel.dao.mapper.BookingRoomTypeI18nJoinMapper;
import com.khodko.RoyalHotel.dao.mapper.BookingRoomTypeI18nUserJoinMapper;
import com.khodko.RoyalHotel.dao.querydsl.OrderBy;
import com.khodko.RoyalHotel.dao.querydsl.Query;
import com.khodko.RoyalHotel.dao.querydsl.Select;
import com.khodko.RoyalHotel.dao.querydsl.where.BaseWhere;
import com.khodko.RoyalHotel.dao.schema.BookingTable;
import com.khodko.RoyalHotel.dao.schema.RoomTypeI18nTable;
import com.khodko.RoyalHotel.dao.schema.RoomTypeTable;
import com.khodko.RoyalHotel.dao.schema.UserTable;
import com.khodko.RoyalHotel.localization.Localization;
import com.khodko.RoyalHotel.dao.core.QueryDslDao;

import static com.khodko.RoyalHotel.dao.schema.BookingTable.*;
import static com.khodko.RoyalHotel.dao.querydsl.where.Where.*;

import com.khodko.RoyalHotel.model.Booking;
import com.khodko.RoyalHotel.util.DbUtil;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class BookingDao extends QueryDslDao<Booking> implements BookingDaoApi {
	
	private final RoomTypeTable roomTypeTable = RoomTypeTable.INSTANCE;
	private final RoomTypeI18nTable roomTypeI18nTable = RoomTypeI18nTable.INSTANCE;
	private final UserTable userTable = UserTable.INSTANCE;
	              
    public BookingDao(ProxyConnection connection) {
        super(connection, BookingTable.INSTANCE, new BookingMapper());                        
    }  
    
    @Override
    public boolean existsRoomType(int roomTypeId) throws SQLException {
    	Query query = new Select(table).where(eq(ROOM_TYPE_ID, roomTypeId));
        return existsQuery(query);
    }
    
    /**
     * 
     */
    @Override
    public int countAll(BaseWhere where) throws SQLException {
    	Query query = new Select("COUNT(*)", table).where(where);
    	return countQuery(query);
    }
    
    /**
     * 
     */    
    @Override
    public List<Booking> findAll(int i18nId, int limit, int offset, BaseWhere where, OrderBy orderBy) throws SQLException {
    	List<Booking> defList = findAllDefault(limit, offset, where, orderBy);
    	if (i18nId == Localization.DEFAULT_I18N) return defList;
    	List<Booking> i18nList = findAllI18n(i18nId, limit, offset, DbUtil.getIds(defList));
    	return DbUtil.joinLists(defList, i18nList);  
    }
    
    private List<Booking> findAllDefault(int limit, int offset, BaseWhere where, OrderBy orderBy) throws SQLException {
    	Select query = findAllQuery(eq(RoomTypeI18nTable.I18N_ID, Localization.DEFAULT_I18N), where)
    			.orderBy(orderBy)
    			.limit(limit, offset);
    	return executeQuery(query, new BookingRoomTypeI18nUserJoinMapper());
    }
    
    private List<Booking> findAllI18n(int i18nId, int limit, int offset, Object[] ids) throws SQLException {
    	if (ids == null || ids.length == 0) return new ArrayList<>();
    	Select query = findAllQuery(
    			in(ID, ids),
    			eq(RoomTypeI18nTable.I18N_ID, i18nId));
    	return executeQuery(query, new BookingRoomTypeI18nUserJoinMapper());
    }
   
    private Select findAllQuery(BaseWhere... where) {
    	return new Select(table, roomTypeTable, roomTypeI18nTable, userTable)
    			.leftJoin(roomTypeTable, ROOM_TYPE_ID, RoomTypeTable.ID)
    			.leftJoin(roomTypeI18nTable, RoomTypeTable.ID, RoomTypeI18nTable.ROOM_TYPE_ID)
    			.leftJoin(userTable, USER_ID, UserTable.ID)
    			.where(where);
    }
    
    /**
     * 
     */
    @Override
    public Optional<Booking> findById(int id, int i18nId) throws SQLException {
    	Optional<Booking> opt = findByIdI18n(id, i18nId);
    	return opt.isPresent() ? opt : findByIdI18n(id, Localization.DEFAULT_I18N);
    }

    private Optional<Booking> findByIdI18n(int id, int i18nId) throws SQLException {
    	Query query = findAllQuery(
    			eq(ID, id), eq(RoomTypeI18nTable.I18N_ID, i18nId));
    	return executeSingleResultQuery(query, new BookingRoomTypeI18nUserJoinMapper());		
    }
     
    /**
     *    
     */
    @Override
    public int countByUserId(int id, BaseWhere where) throws SQLException {
    	Query query = new Select("COUNT(*)", table)
    			.where(eq(USER_ID, id), where);
    	return countQuery(query);
    }
    
    /**
     * 
     */
    @Override
    public List<Booking> findAllByUserId(int i18nId, int userId, int limit, int offset, BaseWhere where, OrderBy orderBy) throws SQLException {
    	List<Booking> defList = findAllByUserIdDefault(userId, limit, offset, where, orderBy);
    	if (i18nId == Localization.DEFAULT_I18N) return defList;
    	List<Booking> i18nList = findAllByUserIdI18n(i18nId, userId, limit, offset, DbUtil.getIds(defList));
    	return DbUtil.joinLists(defList, i18nList);	
    }
    
    private List<Booking> findAllByUserIdDefault(int userId, int limit, int offset, BaseWhere where, OrderBy orderBy) throws SQLException {
    	Select query = findAllByUserId(userId, 
    			eq(RoomTypeI18nTable.I18N_ID, Localization.DEFAULT_I18N).and(where))
    			.orderBy(orderBy)
    			.limit(limit, offset);
    	return executeQuery(query, new BookingRoomTypeI18nJoinMapper());
    }
    
    private List<Booking> findAllByUserIdI18n(int i18nId, int userId, int limit, int offset, Object[] ids) throws SQLException {
    	if (ids == null || ids.length == 0) return new ArrayList<>();
    	Select query = findAllByUserId(userId, 
    			in(ID, ids).and(eq(RoomTypeI18nTable.I18N_ID, i18nId)));
    	return executeQuery(query, new BookingRoomTypeI18nJoinMapper());
    }

    private Select findAllByUserId(int userId, BaseWhere where) {
    	return new Select(table, roomTypeTable, roomTypeI18nTable)
    			.leftJoin(roomTypeTable, ROOM_TYPE_ID, RoomTypeTable.ID)
    			.leftJoin(roomTypeI18nTable, RoomTypeTable.ID, RoomTypeI18nTable.ROOM_TYPE_ID)
	    		.where(eq(USER_ID, userId), where);
    }
       
    /**
     * 
     */
    @Override
    public List<Booking> findByDate(Date arrivalDate, Date departDate) throws SQLException {
    	Query query = new Select(table).where(
    			btwn(ARRIVAL_DATE, arrivalDate, departDate)
    			.or(btwn(DEPART_DATE, arrivalDate, departDate))
    			.or(pbtwn(arrivalDate, ARRIVAL_DATE, DEPART_DATE))
    			.or(pbtwn(departDate, ARRIVAL_DATE, DEPART_DATE)));
    	return executeQuery(query);    	
    }	
   
}

package com.khodko.RoyalHotel.dao.impl;

import com.khodko.RoyalHotel.connection.ProxyConnection;
import com.khodko.RoyalHotel.dao.RoomTypeDaoApi;
import com.khodko.RoyalHotel.dao.mapper.RoomTypeI18nJoinMapper;
import com.khodko.RoyalHotel.dao.mapper.RoomTypeMapper;
import com.khodko.RoyalHotel.dao.querydsl.OrderBy;
import com.khodko.RoyalHotel.dao.querydsl.Query;
import com.khodko.RoyalHotel.dao.querydsl.Select;
import com.khodko.RoyalHotel.dao.querydsl.where.BaseWhere;
import com.khodko.RoyalHotel.dao.schema.BookingTable;
import com.khodko.RoyalHotel.dao.schema.RoomTypeI18nTable;
import com.khodko.RoyalHotel.dao.schema.RoomTypeTable;
import com.khodko.RoyalHotel.localization.Localization;

import static com.khodko.RoyalHotel.dao.schema.BookingTable.ARRIVAL_DATE;
import static com.khodko.RoyalHotel.dao.schema.BookingTable.DEPART_DATE;
import static com.khodko.RoyalHotel.dao.schema.RoomTypeTable.*;
import com.khodko.RoyalHotel.model.RoomType;
import com.khodko.RoyalHotel.util.DbUtil;
import com.khodko.RoyalHotel.dao.core.QueryDslDao;
import static com.khodko.RoyalHotel.dao.querydsl.where.Where.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class RoomTypeDao extends QueryDslDao<RoomType> implements RoomTypeDaoApi {
	
	private final RoomTypeI18nTable roomTypeI18nTable = RoomTypeI18nTable.INSTANCE;
                   
    public RoomTypeDao(ProxyConnection connection) {
        super(connection, RoomTypeTable.INSTANCE, new RoomTypeMapper());
    }
       
    /**
     * 
     */
    private Select subQueryDateFilter(Optional<Date> arrivalDateOpt, Optional<Date> departDateOpt) {    	    	    	
    	Date arrivalDate = (arrivalDateOpt.isPresent()) ? arrivalDateOpt.get() : departDateOpt.get();
    	Date departDate = (departDateOpt.isPresent()) ? departDateOpt.get() : arrivalDateOpt.get();   	   	
    	
    	return new Select("COUNT(*)", BookingTable.INSTANCE).where(
    			eqc(BookingTable.ROOM_TYPE_ID, RoomTypeTable.ID),
    			br(
	    			btwn(ARRIVAL_DATE, arrivalDate, departDate)
	    			.or(btwn(DEPART_DATE, arrivalDate, departDate))
	    			.or(pbtwn(arrivalDate, ARRIVAL_DATE, DEPART_DATE))
	    			.or(pbtwn(departDate, ARRIVAL_DATE, DEPART_DATE)
	    		)));
    }
    
    
	@Override
    public List<RoomType> findAll(int i18nId, Optional<Date> arrivalDateOpt, Optional<Date> departDateOpt, OrderBy orderBy, boolean onlyAccesible) throws SQLException {   
    	List<RoomType> defList = findAllDefault(arrivalDateOpt, departDateOpt, orderBy, onlyAccesible);
    	if (i18nId == Localization.DEFAULT_I18N) return defList;
    	List<RoomType> i18nList = findAllI18n(i18nId, DbUtil.getIds(defList));
    	return DbUtil.joinLists(defList, i18nList);    		
    }
	
	private List<RoomType> findAllDefault(Optional<Date> arrivalDateOpt, Optional<Date> departDateOpt, OrderBy orderBy, boolean onlyAccesible) throws SQLException {    	   	
		BaseWhere accessWhere = onlyAccesible ? eq(RoomTypeTable.ACCESS, true) : null;
		
		BaseWhere dateWhere = null;
    	if (arrivalDateOpt.isPresent() || departDateOpt.isPresent()) {
    		dateWhere = gt(RoomTypeTable.ROOMS, subQueryDateFilter(arrivalDateOpt, departDateOpt));
    	} 
    	Query query = findAllQuery(
    			eq(RoomTypeI18nTable.I18N_ID, Localization.DEFAULT_I18N), accessWhere, dateWhere
    		).orderBy(orderBy);  
    	return executeQuery(query, new RoomTypeI18nJoinMapper());
    }
	
	private List<RoomType> findAllI18n(int i18nId, Object[] ids) throws SQLException {
    	if (ids == null || ids.length == 0) return new ArrayList<>();
    	Query query = findAllQuery(in(ID, ids), eq(RoomTypeI18nTable.I18N_ID, i18nId));
    	return executeQuery(query, new RoomTypeI18nJoinMapper());
    }
    
    private Select findAllQuery(BaseWhere... where) {
    	return new Select(table, roomTypeI18nTable)
    			.leftJoin(roomTypeI18nTable, RoomTypeI18nTable.ROOM_TYPE_ID, ID).where(where);
    }
          
    /**
     * 
     */
    @Override
    public Optional<RoomType> findById(int id, int i18nId) throws SQLException {   	
    	Optional<RoomType> opt = findByIdI18n(id, i18nId);    	
    	return opt.isPresent() ?  opt : findByIdI18n(id, Localization.DEFAULT_I18N);    	
    }
    
    private Optional<RoomType> findByIdI18n(int id, int i18nId) throws SQLException {   	
    	Query query = new Select(table, roomTypeI18nTable)
    			.leftJoin(roomTypeI18nTable, RoomTypeI18nTable.ROOM_TYPE_ID, ID)
    			.where(eq(ID, id), eq(RoomTypeI18nTable.I18N_ID, i18nId));
    	return executeSingleResultQuery(query, new RoomTypeI18nJoinMapper());
    }
        
}

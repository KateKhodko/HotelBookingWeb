package com.khodko.RoyalHotel.dao.impl;

import com.khodko.RoyalHotel.connection.ProxyConnection;
import com.khodko.RoyalHotel.dao.AmenityDaoApi;
import com.khodko.RoyalHotel.dao.mapper.AmenityI18nJoinMapper;
import com.khodko.RoyalHotel.dao.mapper.AmenityMapper;
import com.khodko.RoyalHotel.dao.querydsl.Query;
import com.khodko.RoyalHotel.dao.querydsl.Select;
import com.khodko.RoyalHotel.dao.querydsl.where.BaseWhere;

import static com.khodko.RoyalHotel.dao.querydsl.where.Where.*;
import com.khodko.RoyalHotel.dao.schema.AmenityI18nTable;
import com.khodko.RoyalHotel.dao.schema.AmenityRoomTypeTable;
import com.khodko.RoyalHotel.dao.schema.AmenityTable;
import com.khodko.RoyalHotel.localization.Localization;

import static com.khodko.RoyalHotel.dao.schema.AmenityTable.*;
import com.khodko.RoyalHotel.model.Amenity;
import com.khodko.RoyalHotel.util.DbUtil;
import com.khodko.RoyalHotel.dao.core.QueryDslDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AmenityDao extends QueryDslDao<Amenity> implements AmenityDaoApi {
	
	private final AmenityI18nTable amenityI18nTable = AmenityI18nTable.INSTANCE;
	private final AmenityRoomTypeTable amenityRoomTypeTable = AmenityRoomTypeTable.INSTANCE;
          
    public AmenityDao(ProxyConnection connection) {
        super(connection, AmenityTable.INSTANCE, new AmenityMapper());                            		               
    }
       
    /**
     * 
     */
    @Override
    public List<Amenity> findAll(int i18nId) throws SQLException {   	
    	List<Amenity> defList = findAllDefault();  	
    	if (i18nId == Localization.DEFAULT_I18N) return defList;
    	List<Amenity> i18nList = findAllI18n(i18nId, DbUtil.getIds(defList));
    	return DbUtil.joinLists(defList, i18nList);    	
    }
          
    private List<Amenity> findAllDefault() throws SQLException {
    	Query query = findAllQuery(
    			eq(AmenityI18nTable.I18N_ID, Localization.DEFAULT_I18N));    	
    	return executeQuery(query, new AmenityI18nJoinMapper());
    }
    
    private List<Amenity> findAllI18n(int i18nId, Object[] ids) throws SQLException {
    	if (ids == null || ids.length == 0) return new ArrayList<>();
    	Query query = findAllQuery(
    			eq(AmenityI18nTable.I18N_ID, i18nId), 
    			in(ID, ids));
    	return executeQuery(query, new AmenityI18nJoinMapper());
    }
    
    private Select findAllQuery(BaseWhere... where) {
    	return new Select(table, amenityI18nTable)
    			.leftJoin(amenityI18nTable, AmenityI18nTable.AMENITY_ID, ID)
    			.where(where);
    }
        
    /**
     *          
     */
    @Override
    public List<Amenity> findAllByRoomType(int roomTypeId, int i18nId) throws SQLException {   	   	    	    	
    	List<Amenity> defList = findAllByRoomTypeDefault(roomTypeId);
    	if (i18nId == Localization.DEFAULT_I18N) return defList;
    	List<Amenity> i18nList = findAllByRoomTypeI18n(roomTypeId, i18nId, DbUtil.getIds(defList));
    	return DbUtil.joinLists(defList, i18nList);     	
    }
    
    private List<Amenity> findAllByRoomTypeDefault(int roomTypeId) throws SQLException {
    	Query query = findAllByRoomTypeQuery(roomTypeId, 
    			eq(AmenityI18nTable.I18N_ID, Localization.DEFAULT_I18N));      
    	return executeQuery(query, new AmenityI18nJoinMapper());
    }
    
    private List<Amenity> findAllByRoomTypeI18n(int roomTypeId, int i18nId, Object[] ids) throws SQLException {
    	if (ids == null || ids.length == 0) return new ArrayList<>();
    	Query query = findAllByRoomTypeQuery(roomTypeId, 
    			eq(AmenityI18nTable.I18N_ID, i18nId).and(in(ID, ids)));    	    	
    	return executeQuery(query, new AmenityI18nJoinMapper());
    }
    
    private Select findAllByRoomTypeQuery(int roomTypeId, BaseWhere where) {
    	return new Select(table, amenityI18nTable)
    			.leftJoin(amenityI18nTable, AmenityI18nTable.AMENITY_ID, ID)
    			.leftJoin(amenityRoomTypeTable, AmenityRoomTypeTable.AMENITY_ID, ID)
    			.where(eq(AmenityRoomTypeTable.ROOM_TYPE_ID, roomTypeId), where);
    }
    
    /**
     *        
     */
    @Override
    public List<Amenity> findByRoomType(int roomType) throws SQLException {
    	Query query = new Select(table)
    			.leftJoin(amenityRoomTypeTable, AmenityRoomTypeTable.AMENITY_ID, ID)
    			.where(eq(AmenityRoomTypeTable.ROOM_TYPE_ID, roomType));
    	return executeQuery(query);    	
    }
        
}

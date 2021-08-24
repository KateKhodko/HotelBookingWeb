package com.khodko.RoyalHotel.dao.impl;

import java.sql.SQLException;
import com.khodko.RoyalHotel.connection.ProxyConnection;
import com.khodko.RoyalHotel.dao.AmenityRoomTypeDaoApi;
import com.khodko.RoyalHotel.dao.mapper.AmenityRoomTypeMapper;
import com.khodko.RoyalHotel.dao.querydsl.Delete;
import com.khodko.RoyalHotel.dao.querydsl.Query;
import com.khodko.RoyalHotel.dao.querydsl.Select;
import com.khodko.RoyalHotel.dao.schema.AmenityRoomTypeTable;
import static com.khodko.RoyalHotel.dao.schema.AmenityRoomTypeTable.*;
import com.khodko.RoyalHotel.model.AmenityRoomType;
import com.khodko.RoyalHotel.dao.core.QueryDslDao;
import static com.khodko.RoyalHotel.dao.querydsl.where.Where.*;


public class AmenityRoomTypeDao extends QueryDslDao<AmenityRoomType> implements AmenityRoomTypeDaoApi {
		
    public AmenityRoomTypeDao(ProxyConnection connection) {
        super(connection, AmenityRoomTypeTable.INSTANCE, new AmenityRoomTypeMapper());      
    }

    @Override
    public boolean existsAmenity(int id) throws SQLException {
    	Query query = new Select(table).where(eq(AMENITY_ID, id));
        return existsQuery(query);
    }
    
    @Override
    public void deleteByRoomType(int roomTypeId) throws SQLException {
    	Query query = new Delete(table).where(eq(ROOM_TYPE_ID, roomTypeId));   	
    	delete(query);       
    }        
    
    @Override
    public void delete(Integer id) throws SQLException {       
    }
    
    @Override
    public AmenityRoomType update(AmenityRoomType entity) throws SQLException {   
		return null;	
    }
		   
}

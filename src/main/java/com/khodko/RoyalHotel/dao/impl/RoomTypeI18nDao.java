package com.khodko.RoyalHotel.dao.impl;

import java.sql.SQLException;
import java.util.Optional;

import com.khodko.RoyalHotel.connection.ProxyConnection;
import com.khodko.RoyalHotel.dao.RoomTypeI18nDaoApi;
import com.khodko.RoyalHotel.dao.mapper.RoomTypeI18nMapper;
import com.khodko.RoyalHotel.dao.querydsl.Query;
import com.khodko.RoyalHotel.dao.querydsl.Select;
import com.khodko.RoyalHotel.dao.schema.RoomTypeI18nTable;
import com.khodko.RoyalHotel.dao.core.QueryDslDao;
import static com.khodko.RoyalHotel.dao.schema.RoomTypeI18nTable.*;
import com.khodko.RoyalHotel.model.RoomTypeI18n;
import static com.khodko.RoyalHotel.dao.querydsl.where.Where.*;


public class RoomTypeI18nDao extends QueryDslDao<RoomTypeI18n> implements RoomTypeI18nDaoApi {

    public RoomTypeI18nDao(ProxyConnection connection) {
        super(connection, RoomTypeI18nTable.INSTANCE, new RoomTypeI18nMapper());                        
    }
       		
	@Override
    public boolean existsByName(RoomTypeI18n roomTypeI18n) throws SQLException {
		Query query = new Select(table).where(
				eq(I18N_ID, roomTypeI18n.getI18nId()),
    			eq(NAME, roomTypeI18n.getName()),
    			ne(ID, roomTypeI18n.getId()));
		return existsQuery(query);		
    }
	
	@Override
    public Optional<RoomTypeI18n> findByRoomType(int roomTypeId, int i18nId) throws SQLException {
    	Query query = new Select(table).where(
    			eq(ROOM_TYPE_ID, roomTypeId),
    			eq(I18N_ID, i18nId));
    	return executeSingleResultQuery(query);
    }
			       
}

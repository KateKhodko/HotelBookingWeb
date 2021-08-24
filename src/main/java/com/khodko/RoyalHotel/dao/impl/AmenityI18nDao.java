package com.khodko.RoyalHotel.dao.impl;

import java.sql.SQLException;
import java.util.Optional;
import com.khodko.RoyalHotel.connection.ProxyConnection;
import com.khodko.RoyalHotel.dao.AmenityI18nDaoApi;
import com.khodko.RoyalHotel.dao.mapper.AmenityI18nMapper;
import com.khodko.RoyalHotel.dao.querydsl.Query;
import com.khodko.RoyalHotel.dao.querydsl.Select;
import com.khodko.RoyalHotel.dao.schema.AmenityI18nTable;
import static com.khodko.RoyalHotel.dao.schema.AmenityI18nTable.*;
import com.khodko.RoyalHotel.model.AmenityI18n;
import com.khodko.RoyalHotel.dao.core.QueryDslDao;
import static com.khodko.RoyalHotel.dao.querydsl.where.Where.*;


public class AmenityI18nDao extends QueryDslDao<AmenityI18n> implements AmenityI18nDaoApi {
    
    public AmenityI18nDao(ProxyConnection connection) {
        super(connection, AmenityI18nTable.INSTANCE, new AmenityI18nMapper());                        
    }
    
    @Override
    public Optional<AmenityI18n> findByAmenity(int amenityId, int i18nId) throws SQLException {
    	Query query = new Select(table).where(
    			eq(AMENITY_ID, amenityId), 
    			eq(I18N_ID, i18nId));
    	return executeSingleResultQuery(query);
    }
    
	@Override
    public boolean existsByName(AmenityI18n amenityI18n) throws SQLException {
		Query query = new Select(table).where(
				eq(I18N_ID, amenityI18n.getI18nId()), 
    			eq(NAME, amenityI18n.getName()),
    			ne(ID, amenityI18n.getId()));
		return existsQuery(query);		
    }
    		             
}

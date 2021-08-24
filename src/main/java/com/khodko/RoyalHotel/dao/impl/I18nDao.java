package com.khodko.RoyalHotel.dao.impl;

import java.sql.SQLException;

import com.khodko.RoyalHotel.connection.ProxyConnection;
import com.khodko.RoyalHotel.dao.I18nDaoApi;
import com.khodko.RoyalHotel.dao.mapper.I18nMapper;
import com.khodko.RoyalHotel.dao.querydsl.Query;
import com.khodko.RoyalHotel.dao.querydsl.Select;
import com.khodko.RoyalHotel.dao.schema.I18nTable;

import static com.khodko.RoyalHotel.dao.querydsl.where.Where.*;
import static com.khodko.RoyalHotel.dao.schema.I18nTable.*;

import com.khodko.RoyalHotel.model.I18n;
import com.khodko.RoyalHotel.dao.core.QueryDslDao;


public class I18nDao extends QueryDslDao<I18n> implements I18nDaoApi {
    
    public I18nDao(ProxyConnection connection) {
        super(connection, I18nTable.INSTANCE, new I18nMapper());                        
    }
    
    @Override
    public boolean existsByLocale(I18n i18n) throws SQLException {
		Query query = new Select(table).where(
    			eq(LOCALE, i18n.getLocale()),
    			ne(ID, i18n.getId()));
		return existsQuery(query);		
    }
                   
}

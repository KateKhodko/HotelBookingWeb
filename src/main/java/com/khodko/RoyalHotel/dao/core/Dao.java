package com.khodko.RoyalHotel.dao.core;

import com.khodko.RoyalHotel.model.DbEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T extends DbEntity> {

    T save(T entity) throws SQLException;

    List<T> findAll() throws SQLException;

    Optional<T> findById(Integer id) throws SQLException;

    T update(T entity) throws SQLException;

    void delete(Integer id) throws SQLException;

	int count() throws SQLException;

}

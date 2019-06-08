package edu.kpi.hotel.model.dao.api;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> find(ObjectId id);
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void delete(T entity);
}

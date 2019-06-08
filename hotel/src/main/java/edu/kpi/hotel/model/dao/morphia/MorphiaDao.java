package edu.kpi.hotel.model.dao.morphia;

import dev.morphia.Datastore;
import edu.kpi.hotel.model.dao.api.Dao;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class MorphiaDao<T> implements Dao<T> {

    Datastore datastore;
    Class<T> entityClass;

    @Autowired
    public void setDatastore(Datastore datastore) {
        this.datastore = datastore;
    }

    public MorphiaDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Optional<T> find(ObjectId id) {
        var query = datastore.createQuery(entityClass)
                .filter("_id", id);
        return Optional.ofNullable(query.find().tryNext());
    }

    @Override
    public List<T> findAll() {
        var query = datastore.createQuery(entityClass);
        return query.find().toList();
    }

    @Override
    public void save(T entity) {
        datastore.save(entity);
    }

    @Override
    public void update(T entity) {
        datastore.save(entity);
    }

    @Override
    public void delete(T entity) {
        datastore.delete(entity);
    }
}

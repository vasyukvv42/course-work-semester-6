package edu.kpi.hotel.model.dao.morphia;

import edu.kpi.hotel.model.dao.api.UserDao;
import edu.kpi.hotel.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserMorphiaDao extends MorphiaDao<User> implements UserDao {

    public UserMorphiaDao() {
        super(User.class);
    }

    @Override
    public Optional<User> findByUsernameOrEmail(String usernameOrEmail) {
        var query = datastore.createQuery(entityClass);
        query.or(
                query.criteria("username").equal(usernameOrEmail),
                query.criteria("email").equal(usernameOrEmail)
        );

        return Optional.ofNullable(query.find().tryNext());
    }
}

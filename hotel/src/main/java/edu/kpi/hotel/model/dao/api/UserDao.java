package edu.kpi.hotel.model.dao.api;

import edu.kpi.hotel.model.entity.User;

import java.util.Optional;

public interface UserDao extends Dao<User> {
    Optional<User> findByUsernameOrEmail(String usernameOrEmail);
}

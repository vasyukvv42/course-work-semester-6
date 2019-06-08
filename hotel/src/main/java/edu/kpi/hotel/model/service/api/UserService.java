package edu.kpi.hotel.model.service.api;

import edu.kpi.hotel.model.entity.User;

import java.math.BigDecimal;
import java.util.Optional;

public interface UserService {
    User createAccount(String username, String email, String password);

    void refillBalance(User user, BigDecimal amount);

    Optional<User> findByUsernameOrEmail(String usernameOrEmail);

    Optional<User> currentUser();
}

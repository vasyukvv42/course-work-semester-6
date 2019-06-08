package edu.kpi.hotel.model.service.impl;

import edu.kpi.hotel.model.dao.api.UserDao;
import edu.kpi.hotel.model.entity.User;
import edu.kpi.hotel.model.entity.UserRole;
import edu.kpi.hotel.model.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userRepository;
    private PasswordEncoder encoder;

    @Autowired
    public void setUserRepository(UserDao userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public User createAccount(String username, String email, String password) {
        var user = new User();

        user.setRole(UserRole.CLIENT);
        user.setUsername(username);
        user.setEmail(email);

        var passwordHash = encoder.encode(password);
        user.setPasswordHash(passwordHash);

        userRepository.save(user);

        return user;
    }

    public void refillBalance(User user, BigDecimal amount) {
        if (amount == null || amount.compareTo(new BigDecimal(0)) < 0)
            throw new IllegalArgumentException("Amount must be non-negative");

        var newBalance = user.getBalance().add(amount);
        user.setBalance(newBalance);

        userRepository.update(user);
    }

    @Override
    public Optional<User> findByUsernameOrEmail(String usernameOrEmail) {
        return userRepository.findByUsernameOrEmail(usernameOrEmail);
    }

    @Override
    public Optional<User> currentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsernameOrEmail(username);
    }

}

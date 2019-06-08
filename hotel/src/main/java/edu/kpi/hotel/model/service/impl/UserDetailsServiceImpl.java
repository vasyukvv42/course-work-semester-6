package edu.kpi.hotel.model.service.impl;

import edu.kpi.hotel.model.dao.api.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserDao userRepository;

    @Autowired
    public void setUserRepository(UserDao userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository
                .findByUsernameOrEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user with such username or email"));

        var roles = List.of(user.getRole());

        return new User(
                user.getUsername(),
                user.getPasswordHash(),
                roles
        );
    }
}
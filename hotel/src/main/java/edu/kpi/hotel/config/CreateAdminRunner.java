package edu.kpi.hotel.config;

import com.mongodb.DuplicateKeyException;
import edu.kpi.hotel.model.entity.User;
import edu.kpi.hotel.model.entity.UserRole;
import edu.kpi.hotel.model.dao.api.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CreateAdminRunner implements CommandLineRunner {

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

    public void run(String[] args) {
        var user = new User();

        user.setRole(UserRole.ADMINISTRATOR);
        user.setUsername("seredamedok");
        user.setEmail("sereda@med.ok");


        var passwordHash = encoder.encode("qwerty");
        user.setPasswordHash(passwordHash);

        try {
            userRepository.save(user);
        } catch (DuplicateKeyException ignored) { }
    }
}

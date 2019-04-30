package com.app.service;

import com.app.entities.Role;
import com.app.entities.Status;
import com.app.entities.User;
import com.app.repository.RoleRepository;
import com.app.repository.UserRepository;
import com.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 25.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private  final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User register(User user) {
        Role roleUser = this.roleRepository.findByName("ROLE_USER");
        final List<Role> roles = Arrays.asList(roleUser);

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        user.setStatus(Status.ACTIVE);
        final User registerUser = this.userRepository.save(user);
        log.info("IN register - user: {} successfully registered" ,registerUser);
        return registerUser;
    }


    public List<User> getAll() {
        final List<User> all = this.userRepository.findAll();
        log.info("IN getAll: - {} users found", all.size());
        return all;
    }



    public User findById(Long id) {
        final User user = this.userRepository.findById(id).orElse(null);
        if (user == null) {
            log.warn("IN findById - no user found by id: {}", id);
        }
        log.info("IN findById - user: {} found by id: {}", user, id);
        return user;
    }


    public void delete(Long id) {
        this.userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted", id);
    }
}




package com.app.service;

import com.app.controller.vm.ManagedUserVM;
import com.app.entities.Role;
import com.app.entities.Status;
import com.app.entities.User;
import com.app.exceptions.EmailAlreadyUsedException;
import com.app.exceptions.LoginAlreadyUsedException;
import com.app.repository.RoleRepository;
import com.app.repository.UserRepository;
import com.app.security.AuthoritiesConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 25.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
@Service
@Transactional
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


    public User register(ManagedUserVM userVM) {
        userRepository.findByUsername(userVM.getLogin().toLowerCase()).ifPresent(user -> {
            final boolean removed = removeNonActiveUser(user);
            if (!removed) {
                throw new LoginAlreadyUsedException();
            }
        });

        userRepository.findOneByEmailIgnoreCase(userVM.getEmail()).ifPresent(user -> {
            if (!this.removeNonActiveUser(user)) {
                throw new EmailAlreadyUsedException();
            }
        });

        final List<Role> roles = new ArrayList<>();
        this.roleRepository.findByName(AuthoritiesConstants.USER).ifPresent(roles::add);

        User user = new User();
        user.setUsername(userVM.getLogin());
        user.setFirstName(userVM.getFirstName());
        user.setLastName(userVM.getLastName());
        user.setEmail(userVM.getEmail());
        user.setPassword(this.passwordEncoder.encode(userVM.getPassword()));
        user.setRoles(roles);
        user.setActivateCode(UUID.randomUUID().toString());
        user.setStatus(Status.NOT_ACTIVE);
        final User registerUser = this.userRepository.save(user);
        log.info("IN register - user: {} successfully registered", registerUser);
        return registerUser;
    }

    private boolean removeNonActiveUser(User user) {
        if (!user.getStatus().equals(Status.NOT_ACTIVE)) {
            return false;
        }
        this.userRepository.delete(user);
        this.userRepository.flush();
        return true;
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




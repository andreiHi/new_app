package com.app.security;

import com.app.entities.Status;
import com.app.entities.User;
import com.app.repository.UserRepository;
import com.app.security.jwt.UserNotActivatedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 25.04.2019.
 * @version $Id$.
 * @since 0.1.
 */

@Slf4j
@Component("userDetailsService")
public class JWTUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JWTUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    //todo авторизацию по логину и емаилу
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Authenticating {}", username);

//        if (new EmailValidator().isValid(username, null)) {
//
//        }
        final User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format("User with username %s not found", username)));

        log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
        String lowercaseLogin = username.toLowerCase(Locale.ENGLISH);
        return createSpringSecurityUser(lowercaseLogin, user);
    }


    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, User user) {
        if (user.getStatus() != Status.ACTIVE ) {
            throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
        }
        List<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                grantedAuthorities);
    }
}

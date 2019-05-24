package com.app.controller;

import com.app.controller.vm.ManagedUserVM;
import com.app.entities.User;
import com.app.exceptions.InvalidPasswordException;
import com.app.security.MailService;
import com.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailSender;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 09.05.2019.
 * @version $Id$.
 * @since 0.1.
 */

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class AccountController {

    private final UserService userService;
    private final MailService mailSender;

    public AccountController(UserService userService, MailService mailSender) {
        this.userService = userService;
        this.mailSender = mailSender;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody ManagedUserVM userVM) {
        log.info("In register ManagedUser: {}", userVM);
        if (!this.checkPasswords(userVM.getPassword(), userVM.getConfirmedPassword())) {
          throw new InvalidPasswordException();
        }
        User user = this.userService.register(userVM);
        this.mailSender.sendActivationEmail(user);
    }

    private boolean checkPasswords(String password, String confirmedPassword) {
        return !StringUtils.isEmpty(password) && !StringUtils.isEmpty(confirmedPassword)
                && password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH
                && password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH
                && password.equals(confirmedPassword);


    }

}

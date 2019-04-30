package com.app.security.jwt;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 30.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class UserNotActivatedException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public UserNotActivatedException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserNotActivatedException(String msg) {
        super(msg);
    }
}

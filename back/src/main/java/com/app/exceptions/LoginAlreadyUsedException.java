package com.app.exceptions;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 21.05.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class LoginAlreadyUsedException extends RuntimeException {

    public LoginAlreadyUsedException() {
        super("Login name already used!");
    }
}

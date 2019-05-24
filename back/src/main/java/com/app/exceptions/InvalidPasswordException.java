package com.app.exceptions;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 17.05.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException() {
        super("Incorrect password");
    }
}

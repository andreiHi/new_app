package com.app.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 21.05.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException() {
        super("Email already used");
    }
}

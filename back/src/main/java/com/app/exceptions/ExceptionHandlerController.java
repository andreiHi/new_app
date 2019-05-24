package com.app.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 22.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RequestMapping
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<VndErrors> notFoundException(final EntityNotFoundException e) {
        return error(e, HttpStatus.NOT_FOUND, e.getLocalizedMessage());
    }

    @ExceptionHandler({InvalidPasswordException.class})
    public ResponseEntity<VndErrors>invalidPasswordException(final InvalidPasswordException e) {
        return error(e, HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
    }

    @ExceptionHandler({LoginAlreadyUsedException.class})
    public ResponseEntity<VndErrors>loginAlreadyUsedException(final InvalidPasswordException e) {
        return error(e, HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
    }

    @ExceptionHandler({EmailAlreadyUsedException.class})
    public ResponseEntity<VndErrors>emailAlreadyUsedException(final InvalidPasswordException e) {
        return error(e, HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
    }

    private ResponseEntity<VndErrors> error(
            final Exception exception, final HttpStatus httpStatus, final String logRef) {
        final String message =
                Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity<>(new VndErrors(logRef, message), httpStatus);
    }

}

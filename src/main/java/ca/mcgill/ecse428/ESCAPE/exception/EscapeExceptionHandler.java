package ca.mcgill.ecse428.ESCAPE.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Simple handler for exceptions occurring within the
 * Escape system.
 *
 * @author Roxanne Archambault
 *
 */
@ControllerAdvice
public class EscapeExceptionHandler {

    @ExceptionHandler(EscapeException.class)
    public ResponseEntity<String> handleEventRegistrationException(EscapeException ex) {
        return new ResponseEntity<String>(ex.getMessage(), ex.getStatus());
    }
}
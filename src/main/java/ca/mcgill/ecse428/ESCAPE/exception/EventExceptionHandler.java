package ca.mcgill.ecse428.ESCAPE.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EventExceptionHandler {

    @ExceptionHandler(EventException.class)
    public ResponseEntity<String> handleEventRegistrationException(EventException ex) {
        return new ResponseEntity<String>(ex.getMessage(), ex.getStatus());
    }
}
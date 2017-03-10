package hu.roothema.homework.web.handler;

import hu.roothema.homework.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionConfiguration.class);

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Error> userValidationException(ValidationException e) {
        LOGGER.error("Exception occurred: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> generalExceptionHandler(Exception e) {
        LOGGER.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error("General error"));
    }

}

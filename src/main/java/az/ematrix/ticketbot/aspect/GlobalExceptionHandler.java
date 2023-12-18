package az.ematrix.ticketbot.aspect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        log.error("An error occurred: {}", e.getMessage(), e);
        return new ResponseEntity<>("Service is undergoing maintenance. Please try again later.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException e) {
        log.error("An error occurred: {}", e.getMessage(), e);
        return new ResponseEntity<>("Fill it out correctly", HttpStatus.BAD_REQUEST);
    }
}

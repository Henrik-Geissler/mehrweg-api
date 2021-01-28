package co.mehrwegheld.api.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.InputMismatchException;

@ControllerAdvice
public interface HttpCodeHandler {

    default ResponseEntity<Object> handleError(String reason, HttpStatus status) {
        return new ResponseEntity<>(reason, status);
    }

    default ResponseEntity<Object> handleSuccess() {
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(InputMismatchException.class)
    default ResponseEntity<Object> handleBadRequest(InputMismatchException ime) {
        String reason = ime.getMessage();
        return handleError(reason, HttpStatus.BAD_REQUEST);
    }
}

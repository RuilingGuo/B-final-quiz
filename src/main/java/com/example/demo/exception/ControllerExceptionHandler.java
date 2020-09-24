package com.example.demo.exception;

import com.example.demo.exception.error.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({PeopleNotFoundException.class,GroupNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleNotFound(RuntimeException ex) {
        log.warn("invalid request", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
                .body(new ErrorMessage(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler({TrainerIsNotEnoughException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleTrainerIsNotEnough(RuntimeException ex) {
        log.warn("invalid request", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(new ErrorMessage(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleVaildException(MethodArgumentNotValidException ex) {
        log.warn("invalid request", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(new ErrorMessage(Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage(),
                        HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleVaildException(ConstraintViolationException ex) {
        log.warn("invalid request", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(new ErrorMessage(ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining()),
                        HttpStatus.BAD_REQUEST.value()));
    }

}

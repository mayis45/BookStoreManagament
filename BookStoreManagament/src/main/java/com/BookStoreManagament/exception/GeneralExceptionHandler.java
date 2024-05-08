package com.BookStoreManagament.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return errors;
    }

    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleRoleNotFoundException(RoleNotFoundException ex)
    {
        Map<String, String> errors = new HashMap<>();
        errors.put("errorMessage", ex.getMessage());

        return errors;
    }

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleBookNotFoundException(BookNotFoundException ex)
    {
        Map<String, String> errors = new HashMap<>();
        errors.put("errorMessage", ex.getMessage());

        return errors;
    }

    @ExceptionHandler(PublisherAccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handlePublisherAccountNotFoundException(PublisherAccountNotFoundException ex)
    {
        Map<String, String> errors = new HashMap<>();
        errors.put("errorMessage", ex.getMessage());

        return errors;
    }

    @ExceptionHandler(CartNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleCartNotFoundException(CartNotFoundException ex)
    {
        Map<String, String> errors = new HashMap<>();
        errors.put("errorMessage", ex.getMessage());

        return errors;
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleCategoryNotFoundException(CategoryNotFoundException ex)
    {

        Map<String, String> errors = new HashMap<>();
        errors.put("errorMessage", ex.getMessage());

        return errors;
    }

    @ExceptionHandler(FollowerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleFollowerNotFoundException(FollowerNotFoundException ex)
    {
        Map<String, String> errors = new HashMap<>();
        errors.put("errorMessage", ex.getMessage());

        return errors;
    }

    @ExceptionHandler(DepotNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleDepotNotFoundException(DepotNotFoundException ex)
    {
        Map<String, String> errors = new HashMap<>();
        errors.put("errorMessage", ex.getMessage());

        return errors;
    }
}

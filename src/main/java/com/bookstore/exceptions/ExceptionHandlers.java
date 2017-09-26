package com.bookstore.exceptions;

import com.bookstore.model.BookStoreErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public BookStoreErrorResponse handleUserNotFoundException(final BookNotFoundException ex) {
        return new BookStoreErrorResponse(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
    }

    @ExceptionHandler(BookStoreException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public BookStoreErrorResponse handleBookStoreException(final BookStoreException ex) {
        return new BookStoreErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage());
    }
}
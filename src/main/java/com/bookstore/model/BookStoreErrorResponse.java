package com.bookstore.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookStoreErrorResponse {
    private String code;
    private String message;
}
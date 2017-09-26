package com.bookstore.service;

import com.bookstore.exceptions.BookNotFoundException;
import com.bookstore.exceptions.BookStoreException;
import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BookStoreServiceTest { 

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void findAllTest() {
        Book book = new Book(1l, "test", "test", "test");
        List<Book> expected = new ArrayList<>();
        expected.add(book);
        when(bookRepository.findAll()).thenReturn(expected);
        List<Book> actual = bookService.findAll();
        assertTrue(!actual.isEmpty());
        assertTrue(actual.size() == 1);
        assertEquals(actual.get(0), book);
    }

    @Test(expected = BookStoreException.class)
    public void findAllTest_throwsException() {
        when(bookRepository.findAll()).thenThrow(BookStoreException.class);
        bookService.findAll();
    }

    @Test
    public void findbyId(){
        Book expected = new Book(1234l, "test", "test", "test");
        when(bookRepository.findOne(1234l)).thenReturn(expected);
        Book actual = bookService.findById(1234l);
        assertEquals(actual, expected);
    }

    @Test(expected = BookNotFoundException.class)
    public void findbyId_throwsBookNotFoundException() {
        when(bookRepository.findOne(1234l)).thenReturn(null);
        bookService.findById(1234l);
    }

    @Test
    public void upsert() {
        Book book = new Book(1234l, "test", "test", "test");
        when(bookRepository.save(book)).thenReturn(book);
        Book actual = bookService.upsert(book);
        assertEquals(actual, book);
    }

    @Test(expected = BookStoreException.class)
    public void upsert_throwsBookStoreException() {
        Book book = new Book(1234l, "test", "test", "test");
        when(bookRepository.save(book)).thenThrow(BookStoreException.class);
        bookService.upsert(book);
    }
}

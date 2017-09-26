package com.bookstore.service;


import com.bookstore.exceptions.BookNotFoundException;
import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.verify;
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

    @Test
    public void findbyId() throws BookNotFoundException {
        Book expected = new Book(1234l, "test", "test", "test");
        when(bookRepository.findOne(1234l)).thenReturn(expected);
        Book actual = bookService.findById(1234l);
        assertEquals(actual, expected);
    }

    @Test
    public void upsert() {
        Book book = new Book(1234l, "test", "test", "test");
        when(bookRepository.save(book)).thenReturn(book);
        Book actual = bookService.upsert(book);
        assertEquals(actual, book);
    }
}

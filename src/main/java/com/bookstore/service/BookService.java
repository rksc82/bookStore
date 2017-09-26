package com.bookstore.service;

import com.bookstore.exceptions.BookStoreException;
import com.bookstore.model.Book;
import com.bookstore.exceptions.BookNotFoundException;
import com.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll(){
        try {
            return bookRepository.findAll();
        } catch (Exception e) {
            throw new BookStoreException("Error occured while getting books: " + e);
        }
    }

    public Book upsert(Book book){
        try {
            Long bookId = bookRepository.save(book).getId();
            book.setId(bookId);
            return book;
        } catch (Exception e) {
            throw new BookStoreException( "Error occured while upserting book in store:" + e);
        }
    }

    public Book findById(Long id) {
        Optional<Book> book = Optional.ofNullable(bookRepository.findOne(id));
        if (!book.isPresent()) {
                throw new BookNotFoundException("Book Not found in the store");
        }
        return book.get();
    }

    public void deleteById(Long id) {
        try {
            bookRepository.delete(id);
        } catch (Exception e) {
            throw new BookStoreException("Error occured while deleting book from store:" + e);
        }
    }
}

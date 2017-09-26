package com.bookstore.service;

import com.bookstore.model.Book;
import com.bookstore.exceptions.BookNotFoundException;
import com.bookstore.repository.BookRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book upsert(Book book){
        Long bookId = bookRepository.save(book).getId();
        book.setId(bookId);
        return book;
    }

    public Book findById(Long id) throws BookNotFoundException{
        Optional<Book> book = Optional.ofNullable(bookRepository.findOne(id));
        if(!book.isPresent()) {
            throw new BookNotFoundException("Book Not found");
        }
        return book.get();
    }

    public void deleteById(Long id){
         bookRepository.delete(id);
    }
}

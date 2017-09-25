package com.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> findAll(){
        List<Book> books = bookRepository.findAll();
      return books;
    }

    public Book upsert(Book book){
        Long bookId = bookRepository.save(book).getId();
        book.setId(bookId);
        return book;
    }

    public Book findById(Long id){
        Book book = bookRepository.findOne(id);
        return book;
    }

    public void deleteById(Long id){
         bookRepository.delete(id);
    }

    public Book updateById(Book book) {
        bookRepository.save(book);
        return book;
    }
}

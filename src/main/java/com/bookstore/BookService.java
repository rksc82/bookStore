package com.bookstore;

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
        List<Book> books = bookRepository.findAll();
      return books;
    }

    public Book upsert(Book book){
        Long bookId = bookRepository.save(book).getId();
        book.setId(bookId);
        return book;
    }

    public Book findById(Long id) throws NotFoundException{
        Optional<Book> book = Optional.of(bookRepository.findOne(id));
        if(!book.isPresent()) {
            throw new NotFoundException("Book Not Found:");
        }
        return book.get();
    }

    public void deleteById(Long id){
         bookRepository.delete(id);
    }
}

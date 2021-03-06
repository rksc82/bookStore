package com.bookstore.controller;

import com.bookstore.model.Book;
import com.bookstore.service.BookService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.POST, consumes =MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Book upsertBooks(@RequestBody Book book) {
        return bookService.upsert(book);
    }

    @RequestMapping(method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    @ResponseBody
    public Book findById(@PathVariable Long id) throws NotFoundException {
        return bookService.findById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, produces= MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    @ResponseBody
    public void deleteById(@PathVariable Long id) {
         bookService.deleteById(id);
    }
}

package com.bookstore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @Test
    public void getAllTest() throws Exception {
        Book book = new Book(1234l, "test", "test", "test");
        List books = Arrays.asList(book);
        when(bookService.findAll()).thenReturn(books);

        mvc.perform(get("/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getByIdTest() throws Exception {
        Book book = new Book(1234l, "test", "test", "test");
        when(bookService.findById(1234l)).thenReturn(book);

        mvc.perform(get("/books/1234")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void upsertTest() throws Exception {
        Book book = new Book(10l, "test", "test", "test");
        when(bookService.upsert(book)).thenReturn(book);

        mvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
        .content("{\n" +
                "    \"id\": 10,\n" +
                "    \"name\": \"test\",\n" +
                "    \"author\": \"test\",\n" +
                "    \"description\": \"test\"\n" +
                "}"))
                .andExpect(status().isOk());
    }
}

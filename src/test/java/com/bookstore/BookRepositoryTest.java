package com.bookstore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration()
public class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void findAll(){
        Book expected = new Book(null, "test1" , "test1", "test1");
        entityManager.persist(expected);
        entityManager.flush();

       List<Book> actual =  bookRepository.findAll();
       assertTrue(!actual.isEmpty());
       assertTrue(actual.contains(expected));
       entityManager.remove(expected);
    }
}

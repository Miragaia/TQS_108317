package com.example.lab7_3test_container;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.lab7_3test_container.model.Book;
import com.example.lab7_3test_container.repository.BookRepository;

@Testcontainers
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookIntegrationTest {

    @Container
    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest")
            .withUsername("duke")
            .withPassword("password")
            .withDatabaseName("test");

    @Autowired
    private BookRepository bookRepository;

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @Test
    @Order(1)
    public void testSaveBook() {
        Book book = new Book();
        book.setTitle("Harry Potter e o Calice de Fogo");
        book.setAuthor("J.K. Rowling");
        book.setPages(100);
        bookRepository.save(book);
    }

    @Test
    @Order(2)
    public void testFindBook() {
        Book book = bookRepository.findByTitle("Harry Potter e o Calice de Fogo");
        Assertions.assertNotNull(book);
        Assertions.assertEquals("J.K. Rowling", book.getAuthor());
        Assertions.assertEquals(100, book.getPages());
    }
}


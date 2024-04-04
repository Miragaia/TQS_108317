package com.example.lab7_3test_container.repository;

import com.example.lab7_3test_container.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByTitle(String string);

}


package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

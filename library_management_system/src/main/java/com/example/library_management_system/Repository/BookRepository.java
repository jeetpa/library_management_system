package com.example.library_management_system.Repository;

import com.example.library_management_system.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByTitleIgnoreCase(String title);
    List<Book> findByAuthorIgnoreCase(String author);
    Optional<Book> findByISBN(String ISBN);
    boolean existsByISBN(String ISBN);
    void deleteByISBN(String ISBN);
    List<Book> findByAvailability(boolean availability);
}

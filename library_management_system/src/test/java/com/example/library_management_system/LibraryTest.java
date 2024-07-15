package com.example.library_management_system;

import com.example.library_management_system.Entity.Book;
import com.example.library_management_system.Repository.BookRepository;
import com.example.library_management_system.Service.LibraryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import java.util.List;

import static org.mockito.Mockito.when;

public class LibraryTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private LibraryService libraryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddBook() {
        Book newBook = new Book("New Book", "Author", "ISBN12345", "Fiction", 2023, "General", true);

        // Mocking behavior of repository
        when(bookRepository.existsByISBN(newBook.getISBN())).thenReturn(false);
        when(bookRepository.save(newBook)).thenReturn(newBook);

        Book addedBook = libraryService.addBook(newBook);

        Assertions.assertEquals(newBook, addedBook);
        verify(bookRepository, times(1)).existsByISBN(newBook.getISBN());
        verify(bookRepository, times(1)).save(newBook);
    }

    @Test
    public void testAddBookAlreadyExists() {
        Book existingBook = new Book("Existing Book", "Author", "ISBN12345", "Fiction", 2023, "General", true);

        // Mocking behavior of repository
        when(bookRepository.existsByISBN(existingBook.getISBN())).thenReturn(true);

        Assertions.assertThrows(IllegalArgumentException.class, () -> libraryService.addBook(existingBook));

        verify(bookRepository, times(1)).existsByISBN(existingBook.getISBN());
        verify(bookRepository, never()).save(existingBook);
    }

    @Test
    public void testRemoveBook() {
        String ISBNToRemove = "ISBN12345";

        // Mocking behavior of repository
        doNothing().when(bookRepository).deleteByISBN(ISBNToRemove);

        libraryService.removeBook(ISBNToRemove);

        verify(bookRepository, times(1)).deleteByISBN(ISBNToRemove);
    }

    @Test
    public void testFindBookByTitle() {
        String title = "Book Title";
        Book book1 = new Book(title, "Author1", "ISBN1", "Genre1", 2023, "Department1", true);
        Book book2 = new Book(title, "Author2", "ISBN2", "Genre2", 2024, "Department2", false);

        // Mocking behavior of repository
        when(bookRepository.findByTitleIgnoreCase(title)).thenReturn(Arrays.asList(book1, book2));

        List<Book> foundBooks = libraryService.findBookByTitle(title);

        Assertions.assertEquals(2, foundBooks.size());
        Assertions.assertEquals(book1.getTitle(), foundBooks.get(0).getTitle());
        Assertions.assertEquals(book2.getTitle(), foundBooks.get(1).getTitle());

        verify(bookRepository, times(1)).findByTitleIgnoreCase(title);
    }


}

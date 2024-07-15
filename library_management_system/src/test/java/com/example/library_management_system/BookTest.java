package com.example.library_management_system;

import com.example.library_management_system.Entity.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookTest {

    @Test
    public void testBookConstructor() {
        // Test the constructor and getters
        Book book = new Book("Title", "Author", "1234567890", "Fiction", 2023, "Department", true);
        Assertions.assertEquals("Title", book.getTitle());
        Assertions.assertEquals("Author", book.getAuthor());
        Assertions.assertEquals("1234567890", book.getISBN());
        Assertions.assertEquals("Fiction", book.getGenre());
        Assertions.assertEquals(2023, book.getPublicationYear());
        Assertions.assertEquals("Department", book.getDepartment());
        Assertions.assertTrue(book.isAvailability());
    }

    @Test
    public void testSettersAndGetters() {
        Book book = new Book();
        book.setTitle("New Title");
        book.setAuthor("New Author");
        book.setISBN("0987654321");
        book.setGenre("Non-fiction");
        book.setPublicationYear(2020);
        book.setDepartment("New Department");
        book.setAvailability(false);

        Assertions.assertEquals("New Title", book.getTitle());
        Assertions.assertEquals("New Author", book.getAuthor());
        Assertions.assertEquals("0987654321", book.getISBN());
        Assertions.assertEquals("Non-fiction", book.getGenre());
        Assertions.assertEquals(2020, book.getPublicationYear());
        Assertions.assertEquals("New Department", book.getDepartment());
        Assertions.assertFalse(book.isAvailability());
    }
}

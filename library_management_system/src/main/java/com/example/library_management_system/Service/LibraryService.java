package com.example.library_management_system.Service;

import com.example.library_management_system.Entity.Book;
import com.example.library_management_system.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

    @Autowired
    private BookRepository bookRepository;

    public LibraryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //To add the book and check if it exists with its isbn
    public Book addBook(Book book){
        if(bookRepository.existsByISBN(book.getISBN())){
            throw new IllegalArgumentException("Book with this ISBN already exists.");
        }
        return bookRepository.save(book);
    }

    //To remove book by its isbn
    @Transactional
    public void removeBook(String ISBN) {
        bookRepository.deleteByISBN(ISBN);
    }
    //To return list of books matching with title
    public List<Book> findBookByTitle(String title) {
        return bookRepository.findByTitleIgnoreCase(title);
    }
    //TO return the list of books with the same author
    public List<Book> findBookByAuthor(String author) {
        return bookRepository.findByAuthorIgnoreCase(author);
    }
    //TO return list of all the books
    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }
    //To return the list of all available books
    public List<Book> listAvailableBooks() {
        return bookRepository.findByAvailability(true);
    }

}

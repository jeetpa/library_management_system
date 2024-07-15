package com.example.library_management_system.Menu;

import com.example.library_management_system.Entity.Book;
import com.example.library_management_system.Service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class LibraryMenu implements CommandLineRunner {

    @Autowired
    private LibraryService libraryService;

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Find Book by Title");
            System.out.println("4. Find Book by Author");
            System.out.println("5. List All Books");
            System.out.println("6. List Available Books");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    removeBook(scanner);
                    break;
                case 3:
                    findBookByTitle(scanner);
                    break;
                case 4:
                    findBookByAuthor(scanner);
                    break;
                case 5:
                    listAllBooks();
                    break;
                case 6:
                    listAvailableBooks();
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addBook(Scanner scanner) {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String ISBN = scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter publication year: ");
        int publicationYear = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter department: ");
        String department = scanner.nextLine();
        System.out.print("Is the book available (true/false): ");
        boolean availability = scanner.nextBoolean();

        Book book = new Book(title, author, ISBN, genre, publicationYear, department, availability);
        try {
            libraryService.addBook(book);
            System.out.println("Book added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void removeBook(Scanner scanner) {
        System.out.print("Enter ISBN: ");
        String ISBN = scanner.nextLine();
        libraryService.removeBook(ISBN);
        System.out.println("Book removed successfully.");
    }

    private void findBookByTitle(Scanner scanner) {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        List<Book> books = libraryService.findBookByTitle(title);
        if (books.isEmpty()) {
            System.out.println("No books found with this title.");
        } else {
            books.forEach(book -> System.out.println(book.toString()));
        }
    }

    private void findBookByAuthor(Scanner scanner) {
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        List<Book> books = libraryService.findBookByAuthor(author);
        if (books.isEmpty()) {
            System.out.println("No books found by this author.");
        } else {
            books.forEach(book -> System.out.println(book.toString()));
        }
    }

    private void listAllBooks() {
        List<Book> books = libraryService.listAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            books.forEach(book -> System.out.println(book.toString()));
        }
    }

    private void listAvailableBooks() {
        List<Book> books = libraryService.listAvailableBooks();
        if (books.isEmpty()) {
            System.out.println("No available books in the library.");
        } else {
            books.forEach(book -> System.out.println(book.toString()));
        }
    }
}




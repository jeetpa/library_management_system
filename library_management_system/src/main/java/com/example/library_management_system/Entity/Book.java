package com.example.library_management_system.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    @Column(unique = true)
    private String ISBN;
    private String genre;
    private int publicationYear;
    private String department;
    private boolean availability;

    public Book(String title, String author, String ISBN, String genre, int publicationYear, String department, boolean availability) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.department = department;
        this.availability = availability;
    }
}

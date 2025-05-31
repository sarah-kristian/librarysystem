package com.predd.library.librarysystem.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a book in the library system.
 * <p>
 * Contains bibliographic metadata (title, author, etc.) and an
 * associated {@link LibraryStatus} object for tracking borrowing status.
 * </p>
 */

public class Book {

    private String id; // for MongoDB
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private int publishedYear;

    private LibraryStatus libraryStatus;

    public Book() {
    }

    public Book(String id, String isbn, String title, String author, String publisher, int publishedYear, LibraryStatus libraryStatus) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishedYear = publishedYear;
        this.libraryStatus = libraryStatus;
    }



    @Override
    public String toString() {
        return String.format("%s by %s (%d)", title, author, publishedYear);
    }
}

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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LibraryStatus getLibraryStatus() {
        return libraryStatus;
    }

    public void setLibraryStatus(LibraryStatus libraryStatus) {
        this.libraryStatus = libraryStatus;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    @Override
    public String toString() {
        return String.format("%s by %s (%d)", title, author, publishedYear);
    }

}

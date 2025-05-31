package com.predd.library.librarysystem.model;

import java.time.LocalDate;

/**
 * Represents a record of a book borrowed by a user.
 * <p>
 * Each entry tracks which book was borrowed, when, and whether it has been returned.
 * Feels like overkill to include this, but without it we'd have to include all the info
 * about the book, right??
 * </p>
 */
public class BorrowedBook {

    private String bookId;
    private LocalDate borrowedOn;
    private LocalDate dueDate;
    private boolean returned;

    public BorrowedBook() {
    }

    public BorrowedBook(String bookId, LocalDate borrowedOn, LocalDate dueDate, boolean returned) {
        this.bookId = bookId;
        this.borrowedOn = borrowedOn;
        this.dueDate = dueDate;
        this.returned = returned;
    }

}

package com.phredd.library.librarysystem.model;

import java.time.LocalDate;

/**
 * Represents a record of a book borrowed by a user.
 * <p>
 * Each entry tracks which book was borrowed, when, and whether it has been returned.
 * </p>
 */
public class BorrowedBook {

    private String bookId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private boolean returned;

    public BorrowedBook() {
    }

    public BorrowedBook(String bookId, LocalDate borrowDate, LocalDate dueDate, boolean returned) {
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returned = returned;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}

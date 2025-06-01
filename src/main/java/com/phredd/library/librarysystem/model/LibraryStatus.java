package com.phredd.library.librarysystem.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the current state of a book in the library system.
 * <p>
 * This includes whether the book is available, who it's borrowed by,
 * and any holds that have been placed on it.
 * </p>
 *
 * Note: The holdQueue field is included for future functionality,
 * but is not currently used in the application logic.
 */

public class LibraryStatus {

    private boolean isAvailable;
    private String borrowedByUserId;
    private LocalDate dueDate;
    private List<HoldRequest> holdQueue = new ArrayList<>();

    public LibraryStatus() {
        this.isAvailable = true;
    }

    public LibraryStatus(boolean available, String borrowedByUserId, LocalDate dueDate, List<HoldRequest> holdQueue) {
        this.isAvailable = available;
        this.borrowedByUserId = borrowedByUserId;
        this.dueDate = dueDate;
        this.holdQueue = holdQueue;
    }

    public List<HoldRequest> getHoldQueue() {
        return holdQueue;
    }

    public void setHoldQueue(List<HoldRequest> holdQueue) {
        this.holdQueue = holdQueue;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getBorrowedByUserId() {
        return borrowedByUserId;
    }

    public void setBorrowedByUserId(String borrowedByUserId) {
        this.borrowedByUserId = borrowedByUserId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

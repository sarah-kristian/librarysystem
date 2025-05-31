package com.predd.library.librarysystem.model;

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

    private boolean available;
    private String borrowedByUserId;
    private LocalDate dueDate;
    private List<HoldRequest> holdQueue = new ArrayList<>();

    public LibraryStatus() {
        this.available = true;
    }

    public LibraryStatus(boolean available, String borrowedByUserId, LocalDate dueDate, List<HoldRequest> holdQueue) {
        this.available = available;
        this.borrowedByUserId = borrowedByUserId;
        this.dueDate = dueDate;
        this.holdQueue = holdQueue;
    }

}

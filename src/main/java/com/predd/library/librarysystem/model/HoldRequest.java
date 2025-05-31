package com.predd.library.librarysystem.model;

import java.time.LocalDate;

/**
 * Represents a user's request to place a hold on a book.
 * <p>
 * Hold requests are intended to be stored in a queue
 * inside a {@link LibraryStatus} object, and are processed
 * in the order they were received.
 * </p>
 * <p>
 * This model is included for future functionality and is not yet used
 * in the current application logic.
 * </p>
 */

public class HoldRequest {

    private String userId;
    private LocalDate requestedOn;

    public HoldRequest() {
    }

    public HoldRequest(String userId, LocalDate requestedOn) {
        this.userId = userId;
        this.requestedOn = requestedOn;
    }

}

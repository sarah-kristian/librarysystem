package com.predd.library.librarysystem.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a library user, including both regular users and admins.
 * <p>
 * Users can borrow books (up to a defined limit), and admins have
 * additional privileges such as adding or suspending users, and managing the catalog.
 * </p>
 */
public class User {
    private String id;
    private String name;
    private String email;
    private String role;
    private boolean suspended;
    private List<BorrowedBook> borrowedBooks = new ArrayList<>();

    public User() {
    }

    public User(String id, String name, String email, String role, boolean suspended) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.suspended = suspended;
    }


}

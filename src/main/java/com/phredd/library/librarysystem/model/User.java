package com.phredd.library.librarysystem.model;

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
    private boolean isSuspended;
    private List<BorrowedBook> borrowedBooks = new ArrayList<>();

    public User() {
    }

    public User(String id, String name, String email, String role, boolean status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.isSuspended = status;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean status) {
        this.isSuspended = status;
    }

    public List<BorrowedBook> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<BorrowedBook> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}

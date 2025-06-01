package com.phredd.library.librarysystem.service;

import com.phredd.library.librarysystem.model.Book;
import com.phredd.library.librarysystem.model.BorrowedBook;
import com.phredd.library.librarysystem.model.LibraryStatus;
import com.phredd.library.librarysystem.model.User;
import com.phredd.library.librarysystem.repository.BookRepository;
import com.phredd.library.librarysystem.repository.UserRepository;

import java.time.LocalDate;
import java.util.Optional;

public class LibraryServiceImpl implements LibraryService  {

    private final BookRepository bookRepo;
    private final UserRepository userRepo;

    public LibraryServiceImpl(BookRepository bookRepo, UserRepository userRepo) {
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
    }

    // perform queries


    public User searchByUserId(String userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
    }

    public Book searchByBookId(String bookId) {
        return bookRepo.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found: " + bookId));
    }


    // process book issues and returns

    public boolean processIssue(String bookId, String userId) {
        try {
            Book book = searchByBookId(bookId);
            User user = searchByUserId(userId);
            validateBookIssue(book, user);
            borrowBook(book, user);
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    public boolean processReturn(String bookId, String userId) {
        try {
            Book book = searchByBookId(bookId);
            User user = searchByUserId(userId);
            validateBookReturn(book);
            returnBook(book, user);
            return true;
        } catch (Exception error) {
            return false;
        }
    }


    // validation methods
    private void validateBookIssue(Book book, User user) {
        if (!book.getLibraryStatus().isAvailable()) {
            throw new IllegalStateException("Book is already borrowed");
        }

        if (user.isSuspended()) {
            throw new IllegalStateException("User is suspended");
        }

        //add check to make sure user hasn't reached their limit
    }



// You could add validation to check if the particular user had that book,
// but since you can check the book in either way, seems unneeded
    private void validateBookReturn(Book book) {
        if (book.getLibraryStatus().isAvailable()) {
            throw new IllegalStateException("Book is already checked-in");
        }
    }


    // helper methods
    private void borrowBook(Book book, User user) {
        LocalDate dueDate = LocalDate.now().plusWeeks(2);

        // update book info
        book.getLibraryStatus().setAvailable(false);
        book.getLibraryStatus().setBorrowedByUserId(user.getId());
        book.getLibraryStatus().setDueDate(dueDate);

        bookRepo.save(book);


        // update user info
        user.getBorrowedBooks().add(new BorrowedBook(
                book.getId(),
                LocalDate.now(),
                dueDate,
                false
        ));


        userRepo.save(user);
    }

    private void returnBook(Book book, User user) {
        // update book info
        book.getLibraryStatus().setAvailable(true);
        book.getLibraryStatus().setBorrowedByUserId(null);
        book.getLibraryStatus().setDueDate(null);

        bookRepo.save(book);

        // update user info
        user.getBorrowedBooks().stream()
                .filter(borrowedBook -> borrowedBook.getBookId().equals(book.getId()) )
                .findFirst()
                .ifPresent(borrowedBook -> borrowedBook.setReturned(true));


        userRepo.save(user);

    }


}

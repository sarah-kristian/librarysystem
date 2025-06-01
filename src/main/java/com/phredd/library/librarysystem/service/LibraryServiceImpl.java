package com.phredd.library.librarysystem.service;

import com.phredd.library.librarysystem.model.Book;
import com.phredd.library.librarysystem.model.BorrowedBook;
import com.phredd.library.librarysystem.model.User;
import com.phredd.library.librarysystem.repository.BookRepository;
import com.phredd.library.librarysystem.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;


public class LibraryServiceImpl implements LibraryQueryService, LibraryCirculationService  {

    private final BookRepository bookRepo;
    private final UserRepository userRepo;

    public LibraryServiceImpl(BookRepository bookRepo, UserRepository userRepo) {
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
    }

// ============================
// Search and Query Operations
// ============================

    @Override
    public User searchByUserId(String userId) {
        return userRepo.findUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
    }

    @Override
    public Book searchByBookId(String bookId) {
        return bookRepo.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found: " + bookId));
    }

    @Override
    public List<Book> searchByBookAuthor(String name) {
        return bookRepo.findByAuthor(name);
    }
    @Override
    public List<Book> searchByBookTitle(String title) {
        return bookRepo.findByTitle(title);
    }
    @Override
    public List<Book> getAllBorrowedBooks(){
        return bookRepo.findAllBorrowedBooks();
    }
    @Override
    public List<BorrowedBook> getUserBorrowHistory(String userId){
        User user = searchByUserId(userId);
        return userRepo.findBorrowingHistory(user);
    };
    @Override
    public List<BorrowedBook> getUserActiveBorrows(String userId){
        User user = searchByUserId(userId);
        return userRepo.findBooksOutByUser(user);
    };


// ---------------------------
// Display Utilities
// ---------------------------

    public void displayBooks(List<Book> bookList) {
            if (bookList.isEmpty()) {
                System.out.println("No books are borrowed at this time.");
            } else {
                System.out.println("--- Books ---");
                System.out.println(
                        "--------------------------------------------------------------------------------------------------");
                System.out.println(String.format("%-12s | %-15s | %-30s",
                        "BOOK ID", "AUTHOR", "TITLE"));
                System.out.println(
                        "--------------------------------------------------------------------------------------------------");
                for (Book book : bookList) {
                    System.out.println(String.format("%-12s | %-15s | %-30s",
                            book.getId(),
                            book.getAuthor(),
                            book.getTitle()));
                }
                System.out.println(
                        "--------------------------------------------------------------------------------------------------");
            }
    }


    public void displayBorrowedBooks(List<BorrowedBook> bookList) {
        if (bookList.isEmpty()) {
            System.out.println("No borrowed books found.");
            return;
        }

        System.out.println("--- Borrowed Books ---");
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-12s | %-12s | %-12s | %-10s%n",
                "BOOK ID", "BORROWED ON", "DUE", "RETURNED");
        System.out.println("---------------------------------------------------------------");

        for (BorrowedBook book : bookList) {
            System.out.printf("%-12s | %-12s | %-12s | %-10s%n",
                    book.getBookId(),
                    book.getBorrowDate(),
                    book.getDueDate(),
                    book.isReturned() ? "Yes" : "No");
        }

        System.out.println("---------------------------------------------------------------");
    }


// ============================
// Borrowing and Return Logic
// ============================

    @Override
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

    @Override
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


// ---------------------------
// Validation Helpers
// ---------------------------

    private void validateBookIssue(Book book, User user) {
        if (!book.getLibraryStatus().isAvailable()) {
            throw new IllegalStateException("Book is already borrowed");
        }

        if (user.isSuspended()) {
            throw new IllegalStateException("User is suspended");
        }

        if (user.getActiveBorrowedCount() >= user.getBorrowingLimit()) {
            throw new IllegalStateException("User has reached their borrowing limit");
        }
    }

    private void validateBookReturn(Book book) {
        if (book.getLibraryStatus().isAvailable()) {
            throw new IllegalStateException("Book is already checked-in");
        }
    }


// ---------------------------
// Other Helpers
// ---------------------------

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

        userRepo.saveUserInfo(user);
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

        userRepo.saveUserInfo(user);

    }
}

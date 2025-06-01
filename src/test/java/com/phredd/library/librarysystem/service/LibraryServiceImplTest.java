package com.phredd.library.librarysystem.service;

import com.phredd.library.librarysystem.model.*;
import com.phredd.library.librarysystem.repository.*;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceImplTest {


    private LibraryServiceImpl libraryService;
    private InMemoryBookRepository bookRepo;
    private InMemoryUserRepository userRepo;

    @BeforeEach
    void setUp() {
        bookRepo = new InMemoryBookRepository();
        userRepo = new InMemoryUserRepository();
        libraryService = new LibraryServiceImpl(bookRepo, userRepo);

        Book book1 = new Book("book1", "9780441478125", "The Left Hand of Darkness", "Ursula K. Le Guin", "Ace Books", 1969, new LibraryStatus());
        Book book2 = new Book("book2", "9780340839935", "The Dispossessed", "Ursula K. Le Guin", "Gollancz", 1974, new LibraryStatus());
        Book book3 = new Book("book3", "9780441013593", "Dune", "Frank Herbert", "Ace Books", 1965, new LibraryStatus());
        Book book4 = new Book("book4", "9780345333926", "Ringworld", "Larry Niven", "Del Rey", 1970, new LibraryStatus());
        bookRepo.save(book1);
        bookRepo.save(book2);
        bookRepo.save(book3);
        bookRepo.save(book4);

        User user = new User("user1", "Test User", "test@example.com", "regular", false);
        userRepo.saveUserInfo(user);
    }

    @Test
    void processIssue_validBookAndUser_returnsTrue() {

        // Issue book (should succeed)
        boolean result = libraryService.processIssue("book1", "user1");
        assertTrue(result);

        // updatedBook should no longer be available and should be assigned to user1
        Book updatedBook = bookRepo.findById("book1").get();
        assertFalse(updatedBook.getLibraryStatus().isAvailable());
        assertEquals("user1", updatedBook.getLibraryStatus().getBorrowedByUserId());

        // updatedBook should be in user's borrowedbooks
        User updatedUser = userRepo.findUserById("user1").get();
        assertEquals(1, updatedUser.getBorrowedBooks().size());
    }

    @Test
    void processIssue_userMaxBorrowLimit_returnsFalse() {
        // Get user and update their borrowing limit
        User user = userRepo.findUserById("user1").get();
        user.setBorrowingLimit(1);
        userRepo.saveUserInfo(user);

        // Issue first book (should succeed)
        boolean firstResult = libraryService.processIssue("book1", "user1");
        assertTrue(firstResult, "First book should be successfully issued");

        // Attempt to issue second book (should fail)
        boolean secondResult = libraryService.processIssue("book2", "user1");
        assertFalse(secondResult, "User has already reached max borrowing limit");

        // Book2 should still be available and unassigned
        Book updatedBook = bookRepo.findById("book2").get();
        assertTrue(updatedBook.getLibraryStatus().isAvailable());
        assertNull(updatedBook.getLibraryStatus().getBorrowedByUserId());
    }

    @Test
    void processIssue_userSuspended_returnsFalse() {
        // First, suspend user
        User suspendedUser = new User("user2", "Suspended User", "suspended@example.com", "regular", true);
        userRepo.saveUserInfo(suspendedUser);

        // Attempt to issue a book to suspended user
        boolean result = libraryService.processIssue("book1", "user2");
        assertFalse(result, "Suspended user should not be able to borrow a book");
    }

    @Test
    void processReturn_bookWasBorrowed_returnsTrue() {
        // First, issue the book
        libraryService.processIssue("book1", "user1");
        libraryService.processIssue("book2", "user1");

        // Then, return it
        boolean returnResult = libraryService.processReturn("book1", "user1");
        assertTrue(returnResult, "Book should be successfully returned");

        // Check book status
        Book updatedBook = bookRepo.findById("book1").get();
        assertTrue(updatedBook.getLibraryStatus().isAvailable(), "Returned book should now be marked as available");

        // Check user status
        User updatedUser = userRepo.findUserById("user1").get();
        assertTrue(updatedUser.getBorrowedBooks().stream()
                        .anyMatch(b -> b.getBookId().equals("book1") && b.isReturned()),
                "Expected book1 to be marked as returned in user's borrowed books"
        );

        Book stillBorrowed = bookRepo.findById("book2").get();
        assertFalse(stillBorrowed.getLibraryStatus().isAvailable(), "Book2 should still be borrowed");

    }

    @Test
    void searchByBookTitle_validTitle_returnsCorrectBooks() {
        List<Book> result = libraryService.searchByBookTitle("Dune");

        assertEquals(1, result.size(), "Should return one book with 'Dune' in the title");
        assertEquals("Dune", result.get(0).getTitle());
    }

    @Test
    void searchByUserId_invalidId_throwsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                libraryService.searchByUserId("invalidUser123")
        );

        assertEquals("User not found: invalidUser123", exception.getMessage());
    }
}
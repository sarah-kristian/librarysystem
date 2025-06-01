package com.phredd.library.librarysystem.service;

import com.phredd.library.librarysystem.model.Book;
import com.phredd.library.librarysystem.model.BorrowedBook;
import com.phredd.library.librarysystem.model.User;

import java.util.List;

public interface LibraryQueryService {
    User searchByUserId(String userId);
    Book searchByBookId(String bookId);
    List<Book> searchByBookAuthor(String name);
    List<Book> searchByBookTitle(String title);
    List<Book> getAllBorrowedBooks();
    List<BorrowedBook> getUserBorrowHistory(String userId);
    List<BorrowedBook> getUserActiveBorrows(String userId);
    void displayBooks(List<Book> bookList);
    void displayBorrowedBooks(List<BorrowedBook> bookList);
}

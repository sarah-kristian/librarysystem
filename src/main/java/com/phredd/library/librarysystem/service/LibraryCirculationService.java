package com.phredd.library.librarysystem.service;

import com.phredd.library.librarysystem.model.BorrowedBook;

import java.util.List;

public interface LibraryCirculationService {
    boolean processIssue(String bookId, String userId);
    boolean processReturn(String bookId, String userId);
    List<BorrowedBook> getUserBorrowHistory(String userId);
    List<BorrowedBook> getUserActiveBorrows(String userId);

}

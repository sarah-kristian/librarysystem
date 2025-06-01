package com.phredd.library.librarysystem.service;

public interface LibraryService {
    boolean processIssue(String bookId, String userId);
    boolean processReturn(String bookId, String userId);
}

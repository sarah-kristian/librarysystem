package com.phredd.library.librarysystem.repository;

import com.phredd.library.librarysystem.model.Book;
import com.phredd.library.librarysystem.model.BorrowedBook;
import com.phredd.library.librarysystem.model.User;

import java.util.List;
import java.util.Optional;



public interface UserRepository {

    void saveUserInfo(User user);

    void deleteUserById(String userId);

    Optional<User> findUserById(String userId);

    List<User> findUserByName(String name);

    List<User> findAllUsers();

    List<BorrowedBook> findBooksOutByUser(User user);

    List<BorrowedBook> findBorrowingHistory(User user);
}
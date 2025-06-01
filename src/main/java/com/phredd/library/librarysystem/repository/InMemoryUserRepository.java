package com.phredd.library.librarysystem.repository;

import com.phredd.library.librarysystem.model.Book;
import com.phredd.library.librarysystem.model.BorrowedBook;
import com.phredd.library.librarysystem.model.User;

import java.util.*;
import java.util.stream.Collectors;

/**
 * In-memory implementation of the {@link UserRepository} interface.
 * <p>
 * This class stores User objects in a local HashMap for fast prototyping
 * and testing without relying on a database. It is suitable for early development
 * stages and demos where persistence across sessions is not required.
 * </p>
 * <p>
 * This implementation is intended to be replaced by a persistent
 * data store (e.g., MongoDB) in a production environment.
 * </p>
 */

public class InMemoryUserRepository implements UserRepository {

    private final Map<String, User> users = new HashMap<>();

    @Override
//    “Hey users map — here’s a new entry. Use the user’s ID as the key, and store the full user object as the value.”
    public void saveUserInfo(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public void deleteUserById(String id) {
        users.remove(id);
    }

    @Override
//    “Try to get the user. If it’s null, return an empty box. If it’s there, return a box holding it.”
    public Optional<User> findUserById(String id) {
        return Optional.ofNullable(users.get(id));
    }


    @Override
    public List<User> findUserByName(String name) {
        return users.values().stream()
                .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findAllUsers() {
        return new ArrayList<>(users.values());
    }

    public List<BorrowedBook> findBooksOutByUser(User user){
        return user.getBorrowedBooks().stream()
                .filter(book -> !book.isReturned())
                .collect(Collectors.toList());
    };

    public List<BorrowedBook> findBorrowingHistory(User user){
        return user.getBorrowedBooks();
    };
}

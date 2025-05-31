package com.predd.library.librarysystem.repository;

import com.predd.library.librarysystem.model.Book;

import java.util.*;
import java.util.stream.Collectors;

/**
 * In-memory implementation of the {@link BookRepository} interface.
 * <p>
 * This class stores Book objects in a local HashMap for fast prototyping
 * and testing without relying on a database. It is suitable for early development
 * stages and demos where persistence across sessions is not required.
 * </p>
 * <p>
 * This implementation is intended to be replaced by a persistent
 * data store (e.g., MongoDB or JPA-backed database) in a production environment.
 * </p>
 */

public class InMemoryBookRepository implements BookRepository {

    private final Map<String, Book> books = new HashMap<>();

    @Override
//    “Hey books map — here’s a new entry. Use the book’s ID as the key, and store the full Book object as the value.”
    public void save(Book book) {
        books.put(book.getId(), book);
    }

    @Override
    public void deleteById(String id) {
        books.remove(id);
    }

    @Override
//    “Try to get the book. If it’s null, return an empty box. If it’s there, return a box holding it.”
    public Optional<Book> findById(String id) {
        return Optional.ofNullable(books.get(id));
    }

    @Override
    public List<Book> findByTitle(String title) {
        return books.values().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return books.values().stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }
}

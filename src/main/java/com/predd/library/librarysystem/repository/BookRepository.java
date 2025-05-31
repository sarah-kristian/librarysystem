package com.predd.library.librarysystem.repository;

import com.predd.library.librarysystem.model.Book;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing Book objects.
 * <p>
 * This interface defines the contract for storing, retrieving, updating,
 * and deleting Book records, regardless of how or where the data is stored.
 * </p>
 * <p>
 * Implementations of this interface may use in-memory collections,
 * a database, or an external API. This abstraction allows the service
 * layer to interact with books in a consistent way, promoting clean
 * architecture and separation of concerns.
 * </p>
 */

public interface BookRepository {

    void save(Book book);

    void deleteById(String id);

    Optional<Book> findById(String id);

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findAll();
}
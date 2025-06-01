package com.phredd.library.librarysystem.util;

import com.phredd.library.librarysystem.model.Book;
import com.phredd.library.librarysystem.model.User;
import com.phredd.library.librarysystem.model.LibraryStatus;
import com.phredd.library.librarysystem.repository.InMemoryBookRepository;
import com.phredd.library.librarysystem.repository.InMemoryUserRepository;

public class DataSeeder {

    private final InMemoryBookRepository bookRepository;
    private final InMemoryUserRepository userRepository;


    public DataSeeder(InMemoryBookRepository bookRepository, InMemoryUserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    /**
     * Seeds the system with demo books.
     */
    public void seedBooks() {
        bookRepository.save(new Book("101", "9780441478125", "The Left Hand of Darkness", "Ursula K. Le Guin", "Ace Books", 1969, new LibraryStatus()));
        bookRepository.save(new Book("102", "9780340839935", "The Dispossessed", "Ursula K. Le Guin", "Gollancz", 1974, new LibraryStatus()));
        bookRepository.save(new Book("103", "9780441013593", "Dune", "Frank Herbert", "Ace Books", 1965, new LibraryStatus()));
        bookRepository.save(new Book("104", "9780345333926", "Ringworld", "Larry Niven", "Del Rey", 1970, new LibraryStatus()));
        bookRepository.save(new Book("105", "9781465499857", "DK Eyewitness: Space", "DK Publishing", "DK", 2020, new LibraryStatus()));
        bookRepository.save(new Book("106", "9780744035026", "DKfindout! Earth", "Sarah Cruddas", "DK", 2021, new LibraryStatus()));
        bookRepository.save(new Book("107", "9781465489810", "First Science Encyclopedia", "DK", "DK", 2019, new LibraryStatus()));
        bookRepository.save(new Book("108", "9780744039611", "How Things Work", "T.J. Resler", "DK", 2021, new LibraryStatus()));
        bookRepository.save(new Book("109", "9780310286707", "Systematic Theology", "Wayne Grudem", "Zondervan", 1994, new LibraryStatus()));
        bookRepository.save(new Book("110", "9780802830419", "Institutes of the Christian Religion", "John Calvin", "Eerdmans", 1536, new LibraryStatus()));
        bookRepository.save(new Book("111", "9780801067106", "Desiring God", "John Piper", "Multnomah", 1986, new LibraryStatus()));
        bookRepository.save(new Book("112", "9780618640157", "The Fellowship of the Ring", "J.R.R. Tolkien", "Houghton Mifflin", 1954, new LibraryStatus()));
        bookRepository.save(new Book("113", "9780618645619", "The Two Towers", "J.R.R. Tolkien", "Houghton Mifflin", 1954, new LibraryStatus()));
        bookRepository.save(new Book("114", "9780618651047", "The Return of the King", "J.R.R. Tolkien", "Houghton Mifflin", 1955, new LibraryStatus()));
        System.out.println("Seeded 14 books.");
    }

    public void seedUsers() {
        User adminUser = new User(
                "admin001",
                "Admin Alice",
                "admin@library.local",
                "admin",
                false // isSuspended
        );

        User regularUser1 = new User(
                "user001",
                "User Bob",
                "bob.reader@library.local",
                "regular",
                false
        );

        User regularUser2 = new User(
                "user002",
                "User Carol",
                "carol.pageflipper@library.local",
                "regular",
                false
        );

        userRepository.saveUserInfo(adminUser);
        userRepository.saveUserInfo(regularUser1);
        userRepository.saveUserInfo(regularUser2);
        System.out.println("Seeded 3 users.");
    }
}

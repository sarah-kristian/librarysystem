package com.predd.library.librarysystem;

import com.predd.library.librarysystem.repository.InMemoryBookRepository;
import com.predd.library.librarysystem.repository.InMemoryUserRepository;
import com.predd.library.librarysystem.util.DataSeeder;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibrarySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrarySystemApplication.class, args);

		InMemoryBookRepository bookRepo = new InMemoryBookRepository();
		InMemoryUserRepository userRepo = new InMemoryUserRepository();

		DataSeeder seeder = new DataSeeder(bookRepo, userRepo);

		seeder.seedBooks();
		seeder.seedUsers();
	}
}

package com.bookverse.layered_file_monolith.services;

import com.bookverse.layered_file_monolith.entities.Book; // Book sınıfını içeri aldık
import java.util.List; // Listeyi içeri aldık
import java.util.Optional; // Optional'ı içeri aldık


public interface BookService {
    Optional<Book> findBookById(int id);

    List<Book> findAllBooks();
}

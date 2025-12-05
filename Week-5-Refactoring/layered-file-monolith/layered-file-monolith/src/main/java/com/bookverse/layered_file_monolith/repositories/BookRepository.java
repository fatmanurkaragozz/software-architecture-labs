package com.bookverse.layered_file_monolith.repositories;

import com.bookverse.layered_file_monolith.entities.Book; // Book sınıfını içeri aldık
import java.util.List; // Listeyi içeri aldık
import java.util.Optional; // Optional'ı içeri aldık

public interface BookRepository {
    Optional<Book> findById(int id);

    List<Book> findAll();
}
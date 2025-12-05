package com.bookverse.layered_file_monolith.services;

import com.bookverse.layered_file_monolith.entities.Book;
import com.bookverse.layered_file_monolith.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service (İş Mantığı) Katmanı
 * Tüm iş kuralları, validasyonlar ve hesaplamalar burada yapılır.
 * Controller ile Repository arasında köprü görevi görür.
 */

@Service
public class BookServiceImpl implements BookService {

    // Dependency Injection (Bağımlılık Enjeksiyonu)
    // Servis katmanı, veriye ihtiyaç duyar ama verinin kaynağına (Repository implementation) bağımlı değildir.
    // Sadece arayüze (Interface) bağımlıdır. (Loose Coupling)
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Optional<Book> findBookById(int id) {
        // İŞ MANTIĞI: ID kontrolü burada yapılır.
        if (id <= 0) {
            throw new IllegalArgumentException("Kitap ID'si pozitif bir de!er olmalıdır.");
        }
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }
    
}

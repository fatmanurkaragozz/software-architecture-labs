package com.bookverse.layered_file_monolith.controllers;

import com.bookverse.layered_file_monolith.entities.Book;
import com.bookverse.layered_file_monolith.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller (Sunum) Katmanı
 * Sadece "Trafik Polisi" gibi davranır.
 * 1. İsteği alır.
 * 2. İlgili Servise yönlendirir.
 * 3. Sonucu kullanıcıya döner.
 * İş mantığı VEYA veri erişimi yapmaz.
 */

@RestController
@RequestMapping("/api/books")
public class BooksController {
    private final BookService bookService;

    // Constructor Injection
    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        // İşi servise delege eder.
        return bookService.findAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable int id) {
        try {
            // Hata yakalama (Exception Handling) burada yapılarak kullanıcıya düzgün yanıt dönülür.
            return bookService.findBookById(id)
                    .<ResponseEntity<Object>>map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

package com.bookverse.single_file_monolith;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ❌ ANTIPATTERN: GOD CLASS (Tanrı Sınıf) Örneği
 *
 * Bu sınıf, "Separation of Concerns" (Sorumlulukların Ayrılması) ilkesini ihlal
 * etmektedir.
 * Tek bir sınıf içerisinde:
 * 1. HTTP İsteklerini karşılama (Controller)
 * 2. İş Mantığı kuralları (Service)
 * 3. Veri tutma ve erişim (Repository)
 * sorumluluklarının hepsi bir aradadır.
 */

@RestController
@RequestMapping("/api/books")
public class BooksController {
    // ❌ HATALI TASARIM: Veri kaynağı Controller'ın içine gömülmüş (Tight Coupling).
    // Veritabanı değişirse bu sınıfı değiştirmek zorunda kalırız.
    private static final List<Book> books = new ArrayList<>();
    static {
        books.add(new Book(1, "Yazılım Tasarımı ve Mimarisi", "Özal YILDIRIM"));
        books.add(new Book(2, "Tasarım Desenleri", "Erich Gamma"));
        books.add(new Book(3, "Temiz Kod", "Robert C. Martin"));
    }

    @GetMapping
    public List<Book> getAllBooks() {
        // Doğrudan veri kaynağına erişim yapılıyor.
        return books;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable int id) {
        // ❌ HATALI TASARIM: İş mantığı (Validation) burada yapılmamalı.
        // Bu kodun tekrar kullanılabilirliği (Reusability) yoktur.
        if (id <= 0) {
            return ResponseEntity.badRequest().body("ID pozitif bir de!er olmalıdır.");
        }
        // ❌ HATALI TASARIM: Veri erişim mantığı (Filtering) Controller içinde.
        Optional<Book> book = books.stream().filter(b -> b.getId() == id).findFirst();
        if (book.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book.get());
    }
}

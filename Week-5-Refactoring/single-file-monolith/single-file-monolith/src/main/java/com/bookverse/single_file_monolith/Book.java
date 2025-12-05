package com.bookverse.single_file_monolith;

// 1. VARLIK (ENTITY) TANIMI
// Normalde ayrı bir pakette olması gereken varlık sınıfımız.
public class Book {
    private int id;
    private String title;
    private String author;

    // Parametresiz Constructor (Spring bazen ihtiyaç duyar)
    public Book() {
    }

    // Parametreli Constructor (Bunu kullanıyorsun)
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    // --- GETTER Metotları (Hatanın Çözümü Burası) ---
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    // --- SETTER Metotları ---
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
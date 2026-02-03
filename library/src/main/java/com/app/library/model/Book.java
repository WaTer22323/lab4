package com.app.library.model;

public class Book {
    private Long id;
    private String title;
    private String author;
    private int publicationYear;
    private String genre;
    private int availableCopies;

    // Constructor ว่าง (No-args constructor)
    public Book() {}

    // Constructor ที่รับค่าทั้งหมด
    public Book(Long id, String title, String author, int publicationYear, String genre, int availableCopies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.availableCopies = availableCopies;
    }

    // Getter และ Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getPublicationYear() { return publicationYear; }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public int getAvailableCopies() { return availableCopies; }
    public void setAvailableCopies(int availableCopies) { this.availableCopies = availableCopies; }
}
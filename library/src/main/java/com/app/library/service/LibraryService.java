package com.app.library.service;

import com.app.library.model.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LibraryService {
    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private List<BorrowingRecord> borrowingRecords = new ArrayList<>();

    // --- CRUD พื้นฐาน (Lab 3) ---
    public List<Book> getAllBooks() { return books; }
    public void addBook(Book book) { books.add(book); }
    public Optional<Book> getBookById(Long id) { 
        return books.stream().filter(b -> b.getId().equals(id)).findFirst(); 
    }

    // --- ฟังก์ชันเพิ่มเติม (Lab 4) ---
    // ค้นหาตามประเภท (Genre) [cite: 892, 896]
    public List<Book> getBooksByGenre(String genre) {
        return books.stream()
            .filter(book -> book.getGenre().toLowerCase().contains(genre.toLowerCase()))
            .collect(Collectors.toList());
    }

    // ค้นหาตามผู้แต่งและประเภท (Author & Optional Genre) [cite: 907, 910]
    public List<Book> getBooksByAuthorAndGenre(String author, String genre) {
        return books.stream()
            .filter(book -> book.getAuthor().equalsIgnoreCase(author))
            .filter(book -> genre == null || book.getGenre().toLowerCase().contains(genre.toLowerCase()))
            .collect(Collectors.toList());
    }
}
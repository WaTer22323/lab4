package com.app.library.controller;

import com.app.library.model.Book;
import com.app.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    // ดูหนังสือทั้งหมด [cite: 91]
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(libraryService.getAllBooks(), HttpStatus.OK);
    }

    // เพิ่มหนังสือใหม่ [cite: 94, 95]
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        libraryService.addBook(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // Lab 4: ค้นหาตาม Genre (ใช้ RequestParam) [cite: 712, 713]
    @GetMapping("/books/genre")
    public ResponseEntity<List<Book>> getBooksByGenre(@RequestParam String genre) {
        return new ResponseEntity<>(libraryService.getBooksByGenre(genre), HttpStatus.OK);
    }

    // Lab 4: ค้นหาตาม Author (ใช้ PathVariable) [cite: 714, 715]
    @GetMapping("/books/author/{author}")
    public ResponseEntity<List<Book>> getBooksByAuthor(
            @PathVariable String author, 
            @RequestParam(required = false) String genre) {
        return new ResponseEntity<>(libraryService.getBooksByAuthorAndGenre(author, genre), HttpStatus.OK);
    }
}
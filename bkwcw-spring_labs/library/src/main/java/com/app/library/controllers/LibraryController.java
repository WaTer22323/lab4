package com.app.library.controller;

import com.app.library.model.Book;
import com.app.library.model.Member;
import com.app.library.model.BorrowingRecord;
import com.app.library.service.LibraryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LibraryController {

    private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);

    @Autowired
    private LibraryService libraryService;

    // ================= Book Endpoints =================
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(libraryService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = libraryService.getBookById(id);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        libraryService.addBook(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        if (!libraryService.getBookById(id).isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        updatedBook.setId(id);
        libraryService.updateBook(updatedBook);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (!libraryService.getBookById(id).isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        libraryService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ================= Member Endpoints =================
    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMembers() {
        return new ResponseEntity<>(libraryService.getAllMembers(), HttpStatus.OK);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Optional<Member> member = libraryService.getMemberById(id);
        return member.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/members")
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        libraryService.addMember(member);
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member updatedMember) {
        if (!libraryService.getMemberById(id).isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        updatedMember.setId(id);
        libraryService.updateMember(updatedMember);
        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        if (!libraryService.getMemberById(id).isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        libraryService.deleteMember(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ================= BorrowingRecord Endpoints =================
    @GetMapping("/borrowing-records")
    public ResponseEntity<List<BorrowingRecord>> getAllBorrowingRecords() {
        return new ResponseEntity<>(libraryService.getAllBorrowingRecords(), HttpStatus.OK);
    }

    @PostMapping("/borrow")
    public ResponseEntity<BorrowingRecord> borrowBook(@RequestBody BorrowingRecord record) {
        libraryService.borrowBook(record);
        return new ResponseEntity<>(record, HttpStatus.CREATED);
    }

    @PutMapping("/return/{recordId}")
    public ResponseEntity<Void> returnBook(@PathVariable Long recordId) {
        libraryService.returnBook(recordId, LocalDate.now());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // ================= Search Endpoints (Lab 4) =================
    @GetMapping("/books/genre")
    public ResponseEntity<List<Book>> getBooksByGenre(@RequestParam String genre) {
        return new ResponseEntity<>(libraryService.getBooksByGenre(genre), HttpStatus.OK);
    }

    @GetMapping("/books/author/{author}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String author, @RequestParam(required = false) String genre) {
        return new ResponseEntity<>(libraryService.getBooksByAuthorAndGenre(author, genre), HttpStatus.OK);
    }

    @GetMapping("/books/dueondate")
    public ResponseEntity<List<Book>> getBooksDueOnDate(@RequestParam("dueDate") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dueDate) {
        return new ResponseEntity<>(libraryService.getBooksDueOnDate(dueDate), HttpStatus.OK);
    }

    @GetMapping("/bookavailabileDate")
    public ResponseEntity<LocalDate> checkAvailability(@RequestParam Long bookId) {
        LocalDate avlDate = libraryService.checkAvailability(bookId);
        return (avlDate == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(avlDate, HttpStatus.OK);
    }
}

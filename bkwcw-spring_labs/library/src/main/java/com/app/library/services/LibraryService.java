package com.app.library.service;

import com.app.library.model.Book;
import com.app.library.model.Member;
import com.app.library.model.BorrowingRecord;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryService {
    // In-memory storage using ArrayList
    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private List<BorrowingRecord> borrowingRecords = new ArrayList<>();

    // ================= Book Methods =================
    public List<Book> getAllBooks() { return books; }

    public Optional<Book> getBookById(Long id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst();
    }

    public void addBook(Book book) { books.add(book); }

    public void updateBook(Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(updatedBook.getId())) {
                books.set(i, updatedBook);
                break;
            }
        }
    }

    public void deleteBook(Long id) { books.removeIf(book -> book.getId().equals(id)); }

    // ================= Member Methods =================
    public List<Member> getAllMembers() { return members; }

    public Optional<Member> getMemberById(Long id) {
        return members.stream().filter(member -> member.getId().equals(id)).findFirst();
    }

    public void addMember(Member member) { members.add(member); }

    public void updateMember(Member updatedMember) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getId().equals(updatedMember.getId())) {
                members.set(i, updatedMember);
                break;
            }
        }
    }

    public void deleteMember(Long id) { members.removeIf(member -> member.getId().equals(id)); }

    // ================= BorrowingRecord Methods =================
    public List<BorrowingRecord> getAllBorrowingRecords() { return borrowingRecords; }

    public void borrowBook(BorrowingRecord record) {
        record.setBorrowDate(LocalDate.now());
        record.setDueDate(LocalDate.now().plusDays(14));
        borrowingRecords.add(record);

        // Decrease available copies
        getBookById(record.getBook().getId()).ifPresent(book -> 
            book.setAvailableCopies(book.getAvailableCopies() - 1)
        );
    }

    public void returnBook(Long recordId, LocalDate returnDate) {
        for (BorrowingRecord record : borrowingRecords) {
            if (record.getId().equals(recordId)) {
                record.setReturnDate(returnDate);
                // Increase available copies
                getBookById(record.getBook().getId()).ifPresent(book -> 
                    book.setAvailableCopies(book.getAvailableCopies() + 1)
                );
                break;
            }
        }
    }

    // ================= Advanced Search Methods (Lab 4) =================
    public List<Book> getBooksByGenre(String genre) {
        return books.stream()
                .filter(book -> book.getGenre().toLowerCase().contains(genre.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByAuthorAndGenre(String author, String genre) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .filter(book -> genre == null || book.getGenre().toLowerCase().contains(genre.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksDueOnDate(LocalDate dueDate) {
        List<BorrowingRecord> records = borrowingRecords.stream()
                .filter(record -> record.getDueDate().equals(dueDate))
                .collect(Collectors.toList());

        List<Book> booksDue = new ArrayList<>();
        for (BorrowingRecord record : records) {
            getBookById(record.getBook().getId()).ifPresent(booksDue::add);
        }
        return booksDue;
    }

    public LocalDate checkAvailability(Long bookId) {
        Optional<Book> bookOpt = getBookById(bookId);
        if (!bookOpt.isPresent()) return null;

        Book bookToCheck = bookOpt.get();
        if (bookToCheck.getAvailableCopies() >= 1) {
            return LocalDate.now();
        } else {
            List<BorrowingRecord> sortedRecords = borrowingRecords.stream()
                    .filter(record -> record.getBook().getId().equals(bookId))
                    .sorted((b1, b2) -> b1.getDueDate().compareTo(b2.getDueDate()))
                    .collect(Collectors.toList());
            
            return sortedRecords.isEmpty() ? null : sortedRecords.get(0).getDueDate();
        }
    }
}
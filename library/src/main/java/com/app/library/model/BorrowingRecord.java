package com.app.library.model;

public class BorrowingRecord {
    private Long id;
    private Book book;
    private Member member;
    private String borrowDate;
    private String dueDate;

    public BorrowingRecord() {}

    public BorrowingRecord(Long id, Book book, Member member, String borrowDate, String dueDate) {
        this.id = id;
        this.book = book;
        this.member = member;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    // Getter และ Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }

    public String getBorrowDate() { return borrowDate; }
    public void setBorrowDate(String borrowDate) { this.borrowDate = borrowDate; }

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
}
package com.app.library.model;

public class Member {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String startDate; // ใน Lab ใช้ String หรือ LocalDate ก็ได้
    private String endDate;

    public Member() {}

    public Member(Long id, String name, String email, String phoneNumber, String startDate, String endDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getter และ Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
}
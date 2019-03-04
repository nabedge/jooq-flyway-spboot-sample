package com.example.web;

import java.time.Duration;
import java.time.LocalDate;

public class Book {

    private String title;
    private String isbn;
    private LocalDate publishDate;

    public long daysAgo() {
        return Duration.between(publishDate, LocalDate.now()).toDays();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
}

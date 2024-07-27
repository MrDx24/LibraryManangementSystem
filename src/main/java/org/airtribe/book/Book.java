package org.airtribe.book;


import org.shared.Notification;
import org.shared.Observer;

import java.util.ArrayList;
import java.util.List;

public class Book implements Notification {
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    private List<Observer> observers;

    public Book(String title, String author, String isbn, int publicationYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.observers = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }


    public void displayBookDetails() {
        System.out.println("Book Details: " +
                           "\nTitle: " + this.title +
                           "\nAuthor: " + this.author +
                           "\nISBN no: " + this.isbn +
                           "\nPublication Year: " + this.publicationYear);
    }


    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void notifyAvailable() {
        notifyObservers("The book '" + title + "' is now available.");
    }
}

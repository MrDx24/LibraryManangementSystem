package org.airtribe.patron;

import org.airtribe.book.Book;
import org.shared.Observer;

import java.util.ArrayList;
import java.util.List;

public class Patron implements Observer {
    private String name;
    private String id;
    private List<String> borrowingHistory;
    private List<Book> reservedBooks;

    public Patron(String name, String id) {
        this.name = name;
        this.id = id;
        this.borrowingHistory = new ArrayList<>();
        this.reservedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getBorrowingHistory() {
        return borrowingHistory;
    }

    public void addBorrowingHistory(String ISBN) {
        this.borrowingHistory.add(ISBN);
    }

    public void reserveBook(Book book) {
        reservedBooks.add(book);
    }

    public void displayPatronDetails() {
        System.out.println("Book Details: " +
                "\nTitle: " + this.id +
                "\nAuthor: " + this.name);
    }

    @Override
    public void update(String message) {
        System.out.println("Notification for " + getName() + ": " + message);
    }
}

package org.airtribe.library;

import org.airtribe.book.Book;
import org.airtribe.patron.Patron;

import java.util.*;

public class Library {
    private List<Book> inventory;
    private Map<String, Book> borrowedBooks;
    private List<Patron> patrons;

    public Library() {
        this.inventory = new ArrayList<>();
        this.borrowedBooks = new HashMap<>();
        this.patrons = new ArrayList<>();
    }

    // Book management
    public void addBook(Book book) {
        inventory.add(book);
    }

    public void removeBook(Book book) {
        inventory.remove(book);
    }

    public void updateBook(Book oldBook, Book newBook) {
        int index = inventory.indexOf(oldBook);
        if (index != -1) {
            inventory.set(index, newBook);
        }
    }

    //Search books
    public Book searchBookByTitle(String title) {
        for (Book book : inventory) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public Book searchBookByAuthor(String author) {
        for (Book book : inventory) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                return book;
            }
        }
        return null;
    }

    public Book searchBookByIsbn(String isbn) {
        for (Book book : inventory) {
            if (book.getIsbn().equalsIgnoreCase(isbn)) {
                return book;
            }
        }
        return null;
    }

    // Patron management
    public void addPatron(Patron patron) {
        patrons.add(patron);
    }

    public void updatePatron(Patron oldPatron, Patron newPatron) {
        int index = patrons.indexOf(oldPatron);
        if (index != -1) {
            patrons.set(index, newPatron);
        }
    }

    // Search patron by ID
    private Patron searchPatronById(String id) {
        for (Patron patron : patrons) {
            if (patron.getId().equalsIgnoreCase(id)) {
                return patron;
            }
        }
        return null;
    }

    // Lending process
    public void checkoutBook(String isbn, String patronId) {
        Book book = searchBookByIsbn(isbn);
        if (book != null && !borrowedBooks.containsKey(isbn)) {
            borrowedBooks.put(isbn, book);
            Patron patron = searchPatronById(patronId);
            if (patron != null) {
                patron.addBorrowingHistory(isbn);
            }
        }
    }

    public void returnBook(String isbn) {
        Book book = borrowedBooks.remove(isbn);
        if (book != null) {
            book.notifyAvailable(); // Notify that book is available
        }
    }

    // Inventory management
    public void getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>(inventory);
        availableBooks.removeAll(borrowedBooks.values());
        System.out.println("Available books : \nCurrently there are " + availableBooks.size() + " available. ");
        if(!availableBooks.isEmpty()){
            for(Book book: availableBooks) {
                System.out.println(book.getTitle() + ", " + book.getAuthor());
            }
        }
        else {
            System.out.println("No books available");
        }
    }

    public void getBorrowedBooks() {
        System.out.println("Borrowed books : \nCurrently there are " + borrowedBooks.size() + " books borrowed. ");
        if (!borrowedBooks.isEmpty()) {
            for (Book book : borrowedBooks.values()) {
                System.out.println(book.getTitle() + ", " + book.getAuthor());
            }
        }
        else {
            System.out.println("No books borrowed");
        }
    }

    // Reservation management
    public void reserveBook(String isbn, String userId) {
        Book book = searchBookByIsbn(isbn);
        if (book != null) {
            Patron patron = searchPatronById(userId);
            if (patron != null) {
                book.addObserver(patron);
                patron.reserveBook(book);
                System.out.println("You have successfully reserved the book.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }


    // Switch case to handle different operations
    public void handleOperation(int operation, Scanner scanner) {
        switch (operation) {
            case 1:
                System.out.println("--------------------------------------------------");
                System.out.println("Adding Book");
                System.out.println("Enter book details (title, author, ISBN, year): ");
                String title = scanner.next();
                String author = scanner.next();
                String isbn = scanner.next();
                int year = scanner.nextInt();
                addBook(new Book(title, author, isbn, year));
                System.out.println("New book added - " + title +  "!!!");
                break;
            case 2:
                System.out.println("--------------------------------------------------");
                System.out.println("Removing Book");
                System.out.println("Enter ISBN of book to remove: ");
                String removeIsbn = scanner.next();
                Book bookToRemove = searchBookByIsbn(removeIsbn);
                if (bookToRemove != null) {
                    removeBook(bookToRemove);
                } else {
                    System.out.println("Book not found.");
                }
                break;
            case 3:
                System.out.println("--------------------------------------------------");
                System.out.println("Updating book");
                System.out.println("Enter ISBN of book to update: ");
                String oldIsbn = scanner.next();
                Book oldBook = searchBookByIsbn(oldIsbn);
                if (oldBook != null) {
                    System.out.println("Enter new book details (title, author, ISBN, year): ");
                    String newTitle = scanner.next();
                    String newAuthor = scanner.next();
                    String newIsbn = scanner.next();
                    int newYear = scanner.nextInt();
                    updateBook(oldBook, new Book(newTitle, newAuthor, newIsbn, newYear));
                } else {
                    System.out.println("Book not found.");
                }
                break;
            case 4:
                System.out.println("--------------------------------------------------");
                System.out.println("Search book by title");
                System.out.println("Enter title of book to search: ");
                String searchTitle = scanner.next();
                Book bookByTitle = searchBookByTitle(searchTitle);
                if (bookByTitle != null) {
                    System.out.println("Search result: ");
                    bookByTitle.displayBookDetails();
                } else {
                    System.out.println("Book not found.");
                }
                break;
            case 5:
                System.out.println("--------------------------------------------------");
                System.out.println("Search book by author");
                System.out.println("Enter author of book to search: ");
                String searchAuthor = scanner.next();
                Book bookByAuthor = searchBookByAuthor(searchAuthor);
                if (bookByAuthor != null) {
                    System.out.println("Search result: ");
                    bookByAuthor.displayBookDetails();
                } else {
                    System.out.println("Book not found.");
                }
                break;
            case 6:
                System.out.println("--------------------------------------------------");
                System.out.println("Search book by ISBN");
                System.out.println("Enter ISBN of book to search: ");
                String searchIsbn = scanner.next();
                Book bookByIsbn = searchBookByIsbn(searchIsbn);
                if (bookByIsbn != null) {
                    System.out.println("Search result: ");
                    bookByIsbn.displayBookDetails();
                } else {
                    System.out.println("Book not found.");
                }
                break;
            case 7:
                System.out.println("--------------------------------------------------");
                System.out.println("Adding Patron");
                System.out.println("Enter new Patron details (name, ID): ");
                String name = scanner.next();
                String id = scanner.next();
                addPatron(new Patron(name, id));
                System.out.println("New Patron Added - " + id +  "!!!");
                break;

            case 8:
                System.out.println("--------------------------------------------------");
                System.out.println("Updating Patron");
                System.out.println("Enter Patron ID to update: ");
                String PatronId = scanner.next();
                Patron oldPatron = searchPatronById(PatronId);
                if (oldPatron != null) {
                    System.out.println("Enter updated Patron details (name): ");
                    String newName = scanner.next();
                    updatePatron(oldPatron, new Patron(newName, PatronId));
                    System.out.println("Patron Updated - " + PatronId +  "!!!");
                } else {
                    System.out.println("User not found.");
                }
                break;
            case 9:
                System.out.println("--------------------------------------------------");
                System.out.println("Checkout Book");
                System.out.println("Enter ISBN of book to checkout: ");
                String checkoutIsbn = scanner.next();
                System.out.println("Enter user ID: ");
                String userId = scanner.next();
                checkoutBook(checkoutIsbn, userId);
                break;
            case 10:
                System.out.println("--------------------------------------------------");
                System.out.println("Return Book");
                System.out.println("Enter ISBN of book to return: ");
                String returnIsbn = scanner.next();
                returnBook(returnIsbn);
                break;
            case 11:
                System.out.println("--------------------------------------------------");
                System.out.println("Reserve Book");
                System.out.println("Enter ISBN of book to reserve: ");
                String reserveIsbn = scanner.next();
                System.out.println("Enter Patron ID: ");
                String reserveUserId = scanner.next();
                reserveBook(reserveIsbn, reserveUserId);
                break;
            case 12:
                System.out.println("--------------------------------------------------");
                getAvailableBooks();
                break;
            case 13:
                System.out.println("--------------------------------------------------");
                getBorrowedBooks();
                break;
            case 0:
                System.out.println("--------------------------------------------------");
                System.out.println("Thank you for visiting ABC Library. ");
                break;
            default:
                System.out.println("Invalid operation");
        }
    }
}


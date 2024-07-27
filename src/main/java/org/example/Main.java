package org.example;

import org.airtribe.library.Library;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int ch=0;

        Library library = new Library();

        System.out.println("-----------------Welcome to ABC Library------------------------");

        Scanner scanner = new Scanner(System.in);
        int operation;

        do {
            System.out.println("**************************************************");
            System.out.println("Choose an operation:");
            System.out.println("1: Add Book");
            System.out.println("2: Remove Book");
            System.out.println("3: Update Book");
            System.out.println("4: Search Book by Title");
            System.out.println("5: Search Book by Author");
            System.out.println("6: Search Book by ISBN");
            System.out.println("7: Add User");
            System.out.println("8: Update User");
            System.out.println("9: Checkout Book");
            System.out.println("10: Return Book");
            System.out.println("11: Reserve Book");
            System.out.println("12: Available Books");
            System.out.println("13: Borrowed Books");
            System.out.println("0: Exit");
            System.out.println("**************************************************");
            System.out.println("Enter you choice : ");
            operation = scanner.nextInt();
            library.handleOperation(operation, scanner);
        } while (operation != 0);

        scanner.close();

    }
}
package com.abdullayev.apps.controllers;


import com.abdullayev.apps.dao.*;
import com.abdullayev.apps.entity.*;

public class LibraryManagementController {
    public static void main(String[] args) {
        BookDAO bookDAO = new BookDAO();
        LibraryDAO libraryDAO = new LibraryDAO();

//        // Create new libraries
//        Library library1 = new Library("Central Library", "123 Main St");
//        Library library2 = new Library("City Library", "456 Elm St");
//        Library library3 = new Library("University Library", "789 College Ave");
//
//        // Inserting new libraries to the database
//        libraryDAO.insertToDB(library1);
//        libraryDAO.insertToDB(library2);
//        libraryDAO.insertToDB(library3);
//
//        // Create new books
//        Book book1 = new Book("The Catcher in the Rye", "J.D. Salinger", 1951, library1);
//        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", 1960, library2);
//        Book book3 = new Book("1984", "George Orwell", 1949, library1);
//        Book book4 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, library3);
//        Book book5 = new Book("Moby-Dick", "Herman Melville", 1851, library2);
//
//        // Inserting new books to the database
//        bookDAO.insertToBD(book1);
//        bookDAO.insertToBD(book2);
//        bookDAO.insertToBD(book3);
//        bookDAO.insertToBD(book4);
//        bookDAO.insertToBD(book5);

//        bookDAO.deleteFromDB(4);
//        libraryDAO.deleteFromDB(3);

        // Close resources
        bookDAO.closeResources();
        libraryDAO.closeResources();
    }
}
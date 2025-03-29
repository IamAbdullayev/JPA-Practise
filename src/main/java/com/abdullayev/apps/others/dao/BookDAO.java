package com.abdullayev.apps.others.dao;

import com.abdullayev.apps.others.entity.Book;
import com.abdullayev.apps.others.entity.Library;
import jakarta.persistence.*;


public class BookDAO {
    private final EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-learn");
    private final EntityManager entityManager = factory.createEntityManager();
    private final EntityTransaction transaction = entityManager.getTransaction();


    public void insertToBD(Book book) {
        try {
            if (!transaction.isActive()) {
                transaction.begin();
            }
            entityManager.persist(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to insert object", e);
        }
    }


    public Book fetchFromDB(int id) {
        Book book = null;
        try {
            book = entityManager.find(Book.class, id);
            if (book == null) {
                throw new EntityNotFoundException("Book with id: " + id + " not found.");
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching book.", e);
        }
        return book;
    }


    public void updateDB(String newName, String newAuthor, Integer newYear, Integer newLibraryID, int id) {
        try {
            Book book = entityManager.find(Book.class, id);
            if (book == null) {
                throw new EntityNotFoundException("Book with id: " + id + " not found.");
            }
            if (!transaction.isActive()) {
                transaction.begin();
            }
            if (newName != null) book.setName(newName);
            if (newAuthor != null) book.setAuthor(newAuthor);
            if (newYear != null) book.setYear(newYear);
            if (newLibraryID != null) {
                Library newLibrary = entityManager.find(Library.class, newLibraryID);
                if (newLibrary == null) {
                    throw new RuntimeException("Library with id: " + newLibraryID + " not found.");
                }
                book.setLibrary(newLibrary);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("An error occurred while updating book.", e);
        }
    }


    public void deleteFromDB(int id) {
        try {
            Book book = entityManager.find(Book.class, id);
            if (book == null) {
                throw new EntityNotFoundException("Book with id: " + id + " not found.");
            }
            if (!transaction.isActive()) {
                transaction.begin();
            }
            entityManager.remove(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to delete object.", e);
        }
    }


    public void closeResources() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}

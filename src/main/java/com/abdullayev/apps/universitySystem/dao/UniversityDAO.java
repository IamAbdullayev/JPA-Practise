//package com.abdullayev.apps.universitySystem.dao;
//
//import com.abdullayev.apps.universitySystem.entities.University;
//import jakarta.persistence.*;
//
//import java.util.Date;
//
//public class UniversityDAO {
//    EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-learn");
//    EntityManager entityManager = factory.createEntityManager();
//    EntityTransaction transaction = entityManager.getTransaction();
//
//    public void save(University university) {
//        try {
//            if (university == null) {
//                throw new EntityNotFoundException("The university not found.");
//            }
//
//            if (!transaction.isActive())
//                transaction.begin();
//            entityManager.persist(university);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null && transaction.isActive()) {
//                transaction.rollback();
//            }
//            throw new RuntimeException("Something went wrong while persisting university.", e);
//        }
//    }
//
//    public University show(int id) {
//        University university = null;
//
//        try {
//            university = entityManager.find(University.class, id);
//            if (university == null) {
//                throw new EntityNotFoundException("The university with id: " + id + " doesn't exist.");
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Something went wrong while showing university.", e);
//        }
//
//        return university;
//    }
//
//    public void update(String newName, int id) {
//        try {
//            University university = entityManager.find(University.class, id);
//            if (university == null) {
//                throw new EntityNotFoundException("The university with id: " + id + " doesn't exist.");
//            }
//
//            if (!transaction.isActive())
//                transaction.begin();
//            university.setName(newName);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null && transaction.isActive()) {
//                transaction.rollback();
//            }
//            throw new RuntimeException("Something went wrong while updating university.", e);
//        }
//    }
//
//    public void delete(int id) {
//        try {
//            University university = entityManager.find(University.class, id);
//            if (university == null) {
//                throw new EntityNotFoundException("The university with id: " + id + " doesn't exist.");
//            }
//
//            if (!transaction.isActive())
//                transaction.begin();
//            entityManager.remove(university);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null && transaction.isActive()) {
//                transaction.rollback();
//            }
//            throw new RuntimeException("Something went wrong while deleting university.", e);
//        }
//    }
//
//    public void closeResources() {
//        if (entityManager.isOpen()) {
//            entityManager.close();
//        }
//        if (factory.isOpen()) {
//            factory.close();
//        }
//    }
//}

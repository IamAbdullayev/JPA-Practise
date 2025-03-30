package com.abdullayev.apps.universitySystem.dao;

import com.abdullayev.apps.universitySystem.entities.Student;
import jakarta.persistence.*;

public class StudentDAO {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-learn");
    EntityManager entityManager = factory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    public void save(Student student) {
        try {
            if (student == null) {
                throw new EntityNotFoundException("The student not found.");
            }

            if (!transaction.isActive())
                transaction.begin();
            entityManager.persist(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Something went wrong while persisting student.", e);
        }
    }

    public Student show(int id) {
        Student student = null;

        try {
            student = entityManager.find(Student.class, id);
            if (student == null) {
                throw new EntityNotFoundException("The student with id: " + id + " doesn't exist.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong while showing student.", e);
        }

        return student;
    }

    public void update(String newName, String newSurname, Double newAvgGrade, int id) {
        try {
            Student student = entityManager.find(Student.class, id);
            if (student == null) {
                throw new EntityNotFoundException("The student with id: " + id + " doesn't exist.");
            }

            if (!transaction.isActive())
                transaction.begin();
            if (newName != null)
                student.setName(newName);
            if (newSurname != null)
                student.setSurname(newSurname);
            if (newAvgGrade != null)
                student.setAvgGrade(newAvgGrade);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Something went wrong while updating student.", e);
        }
    }

    public void delete(int id) {
        try {
            Student student = entityManager.find(Student.class, id);
            if (student == null) {
                throw new EntityNotFoundException("The student with id: " + id + " doesn't exist.");
            }

            if (!transaction.isActive())
                transaction.begin();
            entityManager.remove(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Something went wrong while deleting student.", e);
        }
    }

    public void closeResources() {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
        if (factory.isOpen()) {
            factory.close();
        }
    }
}

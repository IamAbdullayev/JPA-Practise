package com.abdullayev.apps.universitySystem.dao;

import com.abdullayev.apps.universitySystem.entities.Teacher;
import jakarta.persistence.*;

public class TeacherDAO {
    public EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-learn");
    public EntityManager entityManager = factory.createEntityManager();
    public EntityTransaction transaction = entityManager.getTransaction();

    public void save(Teacher teacher) {
        try {
            if (teacher == null) {
                throw new EntityNotFoundException("The teacher not found.");
            }

            if (!transaction.isActive())
                transaction.begin();
            entityManager.persist(teacher);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Something went wrong while persisting teacher.", e);
        }
    }

    public Teacher show(int id) {
        Teacher teacher = null;

        try {
            teacher = entityManager.find(Teacher.class, id);
            if (teacher == null) {
                throw new EntityNotFoundException("The teacher with id: " + id + " doesn't exist.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong while showing teacher.", e);
        }

        return teacher;
    }

    public void update(String newName, int id) {
        try {
            Teacher teacher = entityManager.find(Teacher.class, id);
            if (teacher == null) {
                throw new EntityNotFoundException("The teacher with id: " + id + " doesn't exist.");
            }

            if (!transaction.isActive())
                transaction.begin();
            if (newName != null)
                teacher.setName(newName);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Something went wrong while updating teacher.", e);
        }
    }

    public void delete(int id) {
        try {
            Teacher teacher = entityManager.find(Teacher.class, id);
            if (teacher == null) {
                throw new EntityNotFoundException("The teacher with id: " + id + " doesn't exist.");
            }

            if (!transaction.isActive())
                transaction.begin();
            entityManager.remove(teacher);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Something went wrong while deleting teacher.", e);
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

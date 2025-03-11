package com.abdullayev.apps.dao;

import com.abdullayev.apps.entity.Student;
import jakarta.persistence.*;


public class CrudStudentJPA {
    EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("jpa-learn");
    EntityManager entityManager = factory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();


    public void insertToBD(Student student) {
        try {
            transaction.begin();
            entityManager.persist(student);
            transaction.commit(); // Коммитит в БД новый объект
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public Student findByID(int id) {
        Student student = null;
        try {
            student = entityManager.find(Student.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    public void update(String name, String surname, Double avgGrade, int id) {
        Student student = null;
        try {
            transaction.begin();
            student = entityManager.find(Student.class, id);
            if (name != null) {
                student.setName(name);
            }
            if (surname != null) {
                student.setSurname(surname);
            }
            if (avgGrade != null) {
                student.setAvgGrade(avgGrade);
            }
            transaction.commit(); // Обеспечивает синхронизацию между объектом java приложения и строкой таблицы
                                  // Изменил объект - изменится и соответсвующая строка в таблице - и наоборот
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public void remove(int id) {
        try {
            transaction.begin();
            Student student = entityManager.find(Student.class, id);
            entityManager.remove(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }


    public void closeResources() {
        if (entityManager != null) {
            entityManager.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

}

package com.abdullayev.apps.hibernate.controllers;


import com.abdullayev.apps.hibernate.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UniversitySystemController {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        try {
            if (!transaction.isActive()) {
                transaction.begin();
            }


            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Something went wrong", e);
        } finally {
            if (session.isOpen()) {
                session.close();
            }
            factory.close();
        }
    }
}

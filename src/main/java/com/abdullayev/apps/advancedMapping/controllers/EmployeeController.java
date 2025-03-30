package com.abdullayev.apps.advancedMapping.controllers;

import com.abdullayev.apps.advancedMapping.entities.Address;
import com.abdullayev.apps.advancedMapping.entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class EmployeeController {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-learn");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            if (!transaction.isActive()) {
                transaction.begin();
            }

            List<String> friends = new ArrayList<>();
            friends.add("Perla");
            friends.add("Linda");
            friends.add("Donni");

            Address address = new Address("7th school", "Golden Road");

            Employee employee = new Employee("Rocky", "Micky", 5000.0, address, friends);

            entityManager.persist(employee);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Something went wrong", e);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
            factory.close();
        }
    }
}

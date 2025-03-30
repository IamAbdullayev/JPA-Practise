package com.abdullayev.apps.advancedMapping.controllers;

import com.abdullayev.apps.advancedMapping.entities.Address;
import com.abdullayev.apps.advancedMapping.entities.Employee;
import com.abdullayev.apps.advancedMapping.entities.Friend;
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
            Friend friend1 = new Friend("Misha", "Nikolai", 21);
            Friend friend2 = new Friend("Lola", "Denis", 19);
            Friend friend3 = new Friend("Sara", "Polina", 37);

            List<Friend> friends = new ArrayList<>();
            friends.add(friend1);
            friends.add(friend2);
            friends.add(friend3);

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

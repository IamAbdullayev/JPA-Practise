package com.abdullayev.apps.universitySystem.controllers;

import com.abdullayev.apps.universitySystem.entities.*;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;

import java.util.List;


public class UniversitySystemController {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-learn");
        EntityManager entityManager = factory.createEntityManager();
//        EntityTransaction transaction = entityManager.getTransaction();

        try {
//            if (!transaction.isActive()) {
//                transaction.begin();
//            }

            // Select uni without students
//            Query query = entityManager.createQuery("SELECT u FROM University u WHERE u.students is empty");
//            List<Student> results = query.getResultList();
//            System.out.println(results);
            //********************************************************************************************************//
            // Select uni with one student
//            Query query = entityManager.createQuery("SELECT u FROM University u WHERE size(u.students) = 1");
//            List<Student> results = query.getResultList();
//            System.out.println(results);
            //********************************************************************************************************//
            // Sort uni by count of students
//            Query query = entityManager.createQuery("SELECT u FROM University u ORDER BY size(u.students)");
//            List<Student> results = query.getResultList();
//            System.out.println(results);
            //********************************************************************************************************//
            // CROSS JOIN
//            Query query = entityManager.createQuery("SELECT u, s FROM University u, Student s");
//            List<Object[]> results = query.getResultList();
//            for (Object[] result : results) {
//                System.out.println(result[0] + " -----> " + result[1]);
//            }
            //********************************************************************************************************//
            // JOIN
//            Query query = entityManager.createQuery("SELECT u, s FROM University u JOIN u.students s");
//            List<Object[]> results = query.getResultList();
//            for (Object[] result : results) {
//                System.out.println(result[0] + " -----> " + result[1]);
//            }
            //********************************************************************************************************//

            // Native Query
//            Query query = entityManager.createNamedQuery("selectWithId");
//            query.setParameter(1, 2);
//
//            University uni = (University) query.getSingleResult();
//
//            System.out.println(uni);


            // CRITERIA API

            // 1 - Creation of Criteria Builder
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            // 2 - Creation of Criteria Query
            CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

            // 3 - Root creation
            Root<University> root = criteriaQuery.from(University.class);

            // 3.1 - Join
            Join<University, Student> join = root.join("students");

            // 3.2 - Condition creation
            Predicate predicate = criteriaBuilder.between(join.get("avgGrade"), 8, 10);

            // 3.3 - Adding condition to Criteria Query
            criteriaQuery.where(predicate);

            // 4 - Adding root to Criteria Query
            criteriaQuery.multiselect(root, join); // SELECT u, s FROM University u JOIN u.students WHERE u.avgGrade BETWEEN 8 AND 10;

            // 5 - Query creation
            TypedQuery<Object[]> query = entityManager.createQuery(criteriaQuery);

            List<Object[]> results = query.getResultList();

            for (Object[] result : results) {
                System.out.println(result[0] + " ----> " + result[1]);
            }


//            transaction.commit();
        } catch (Exception e) {
//            if (transaction != null && transaction.isActive()) {
//                transaction.rollback();
//            }
            throw new RuntimeException("Something went wrong", e);
        } finally {
            entityManager.close();
            factory.close();
        }
    }
}

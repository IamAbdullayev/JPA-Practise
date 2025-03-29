package com.abdullayev.apps.others.dao;

import com.abdullayev.apps.others.entity.House;
import jakarta.persistence.*;

public class HouseDAO {
    EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("jpa-learn");
    EntityManager entityManager = factory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();


    public void insert(House newHouse) {
        try {
            transaction.begin();
            entityManager.persist(newHouse);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public House show(int id) {
        House house = null;
        try {
            house = entityManager.find(House.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return house;
    }

    public void update(String houseName, Integer numberOfRooms, Double houseArea, int id) {
        try {
            transaction.begin();
            House house = entityManager.find(House.class, id);
            if (houseName != null) {
                house.setHouseName(houseName);
            }
            if (numberOfRooms != null) {
                house.setNumberOfRooms(numberOfRooms);
            }
            if (houseArea != null) {
                house.setHouseArea(houseArea);
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
            House house = entityManager.find(House.class, id);
            entityManager.remove(house);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }


    public void closeResources() {
        if (factory != null) {
            factory.close();
        }
        if (entityManager != null) {
            entityManager.close();
        }
    }
}

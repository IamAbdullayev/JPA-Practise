package com.abdullayev.apps.others.dao;

import com.abdullayev.apps.others.entity.Library;
import jakarta.persistence.*;


public class LibraryDAO {
    private final EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-learn");
    private final EntityManager entityManager = factory.createEntityManager();
    private final EntityTransaction transaction = entityManager.getTransaction();


    public void insertToDB(Library library) {
        try {
            if (!transaction.isActive()) { // Проверяем, не активна ли уже транзакция
                // Начинаем транзакцию. Транзакция является необходимым шагом для обеспечения атомарности операций с базой данных.
                transaction.begin();
            }

            // Сохраняем объект library в базе данных:
            // Персистируем (метод persist) объект. Это не сохраняет в базу данных сразу, а делает объект управляемым.
            // Объект library будет отслежен JPA и синхронизирован с базой данных, когда транзакция будет зафиксирована.
            entityManager.persist(library);

            // Подтверждаем изменения. После этого все изменения, сделанные в рамках транзакции, будут сохранены в базе данных.
            transaction.commit();
        } catch (Exception e) {
            // В случае ошибки откатываем транзакцию. Это предотвращает сохранение некорректных данных в базе.
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            // Логируем ошибку для диагностики. Это поможет в отслеживании причин сбоя.
            System.err.println("Error occurred during insert: " + e.getMessage());

            // Перехватываем ошибку и выбрасываем ее дальше, чтобы другие слои могли с ней работать.
            throw new RuntimeException("Failed to insert library", e);
        }
    }


    public Library fetchFromDB(int id) {
        // Инициализируем переменную для хранения библиотеки.
        Library library = null;

        try {
            // Попытка найти библиотеку по переданному идентификатору.
            // entityManager.find() ищет объект в базе данных, если он существует.
            // .find() возвращает объект Library с указанным id, если он существует в базе.
            library = entityManager.find(Library.class, id);

            // Проверка, если объект не найден, выбрасываем исключение.
            // Это позволит обработать ситуацию, когда библиотека с таким ID не существует в базе данных.
            if (library == null) {
                throw new EntityNotFoundException("Library with id " + id + " not found.");
            }
        } catch (Exception e) {
            // Обработка любых других исключений, которые могут произойти во время работы с базой данных.
            // Например, проблемы с соединением с базой данных, неправильные запросы и так далее.
            throw new RuntimeException("An error occurred while fetching library", e);
        }

        // Возвращаем найденную библиотеку.
        // Если библиотека не была найдена, будет выброшено исключение, и этот код не выполнится.
        return library;
    }


    public void updateDB(String newName, String newAddress, int id) {
        try {
            // Находим объект Library по ID
            Library library = entityManager.find(Library.class, id);

            if (library == null) {
                // Если библиотека с таким id не найдена, выбрасываем исключение
                throw new EntityNotFoundException("Library with id " + id + " not found.");
            }

            if (!transaction.isActive()) { // Проверяем, не активна ли уже транзакция
                transaction.begin(); // Начинаем транзакцию
            }

            // Обновляем данные объекта
            if (newName != null) library.setName(newName);
            if (newAddress != null) library.setAddress(newAddress);

            transaction.commit(); // Фиксируем изменения в базе данных
        } catch (Exception e) {
            // В случае ошибки откатываем транзакцию, если она активна
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            // Логируем ошибку (исправлено: update вместо insert)
            System.err.println("Error occurred during update: " + e.getMessage());

            // Пробрасываем исключение выше, чтобы вызывающий код мог обработать его
            throw new RuntimeException("Failed to update library", e);
        }
    }


    public void deleteFromDB(int id) {
        try {
            // Находим объект Library по ID
            Library library = entityManager.find(Library.class, id);

            if (library == null) {
                // Если библиотека с таким id не найдена, выбрасываем исключение
                throw new EntityNotFoundException("Library with id " + id + " not found.");
            }

            if (!transaction.isActive()) { // Проверяем, не активна ли уже транзакция
                // Начинаем транзакцию. Транзакция является необходимым шагом для обеспечения атомарности операций с базой данных.
                transaction.begin();
            }
            // Удаляем объект
            entityManager.remove(library);

            transaction.commit(); // Фиксируем изменения
        } catch (Exception e) {
            // В случае ошибки откатываем транзакцию
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            // Логируем ошибку
            System.err.println("Error occurred during deletion: " + e.getMessage());

            // Пробрасываем исключение
            throw new RuntimeException("Failed to delete library", e);
        }
    }



    public void closeResources() {
        // Проверяем, если entityManager не null и открыт.
        // Это необходимо для предотвращения возможных исключений при попытке закрыть уже закрытый или несуществующий entityManager.
        if (entityManager != null && entityManager.isOpen()) {
            // Закрываем entityManager, если он открыт.
            entityManager.close();
        }

        // Проверяем, если factory не null и открыт.
        // Это нужно для того, чтобы не попытаться закрыть уже закрытую или несуществующую factory.
        if (factory != null && factory.isOpen()) {
            // Закрываем EntityManagerFactory, если он открыт.
            factory.close();
        }
    }

}

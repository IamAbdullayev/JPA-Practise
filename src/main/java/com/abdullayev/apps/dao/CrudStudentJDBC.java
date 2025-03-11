package com.abdullayev.apps.dao;

import com.abdullayev.apps.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CrudStudentJDBC {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/test_db";
    private static final String DB_USER = "jpauser";
    private static final String DB_PASSWORD = "jpapwd";


    public List<Student> index() {
        List<Student> students = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER,DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT * FROM test_db.students")) {

            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getLong("id"));
                    student.setName(rs.getString("name"));
                    student.setSurname(rs.getString("surname"));
                    student.setAvgGrade(rs.getDouble("avg_grade"));

                    students.add(student);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    public Student findById(Long id) {
        Student student = null;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT * FROM test_db.students WHERE id=?")) {

            ps.setLong(1, id);

            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    student = new Student();
                    student.setId(resultSet.getLong("id"));
                    student.setName(resultSet.getString("name"));
                    student.setSurname(resultSet.getString("surname"));
                    student.setAvgGrade(resultSet.getDouble("avg_grade"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return student;
    }

    public void insertToBD(Student student) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO test_db.students (name, surname, avg_grade) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getSurname());
            ps.setDouble(3, student.getAvgGrade());

            int affectedRows = ps.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("Failed to add Student to DB");
            }

            // Синхронизация между добавляемым объектом и строкой в таблице (БД)
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                student.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Failed to create Student ID");
            }
            generatedKeys.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Student updateStudent, Long id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE test_db.students SET name=?, surname=?, avg_grade=? WHERE id=?")) {

            ps.setString(1, updateStudent.getName());
            ps.setString(2, updateStudent.getSurname());
            ps.setDouble(3, updateStudent.getAvgGrade());
            ps.setLong(4, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void delete(Long id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(
                     "DELETE FROM test_db.students WHERE id=?")) {

            ps.setLong(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

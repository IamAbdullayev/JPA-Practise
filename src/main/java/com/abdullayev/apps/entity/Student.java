package com.abdullayev.apps.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;


@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 25, message = "Name should be between 2 and 25")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 25, message = "Name should be between 2 and 25")
    @Column(name = "surname", nullable = false)
    private String surname;

    @Min(value = 0, message = "Average grade should not be less 0")
    @Column(name = "avg_grade", nullable = false)
    private Double avgGrade;

    @Transient
    private LocalDateTime data;


    public Student() {
    }

    public Student(String name, String surname, Double avgGrade) {
        this.name = name;
        this.surname = surname;
        this.avgGrade = avgGrade;
        this.data = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Double getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(Double avgGrade) {
        this.avgGrade = avgGrade;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", avgGrade=" + avgGrade +
                ", data=" + data +
                '}';
    }
}

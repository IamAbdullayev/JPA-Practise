package com.abdullayev.apps.universitySystem.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//@Cacheable
//@Entity
//@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany()
    @JoinTable(
            name = "teacher_uni",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "uni_id")
    )
    private List<University> universities = new ArrayList<>();

    public Teacher() {
    }

    public Teacher(String name) {
        this.name = name;
    }

    public void addUniversityToTeacher(University university) {
        universities.add(university);
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

    public List<University> getUniversities() {
        return universities;
    }

    public void setUniversities(List<University> universities) {
        this.universities = universities;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

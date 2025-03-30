package com.abdullayev.apps.hibernate.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


//@Entity
//@Table(name = "university")
@SqlResultSetMapping(
        name = "UniversityMapping",
        entities = @EntityResult(entityClass = University.class)
)
@NamedNativeQueries(
        {
                @NamedNativeQuery(
                        name = "selectWithId",
                        query = "SELECT * FROM university WHERE university.id = ?1",
                        resultSetMapping = "UniversityMapping"
                ),
                @NamedNativeQuery(
                        name = "selectWithName",
                        query = "SELECT * FROM university WHERE university.name = ?1",
                        resultSetMapping = "UniversityMapping"
                )
        }
)
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "founding_date")
    private String foundingDate;
//
//    @OneToMany(mappedBy = "university")
//    private List<Student> students = new ArrayList<>();

    public University() {}

    public University(String name, String foundingDate) {
        this.name = name;
        this.foundingDate = foundingDate;
    }

//    public void addStudentToUniversity(Student newStudent) {
//        students.add(newStudent);
//        newStudent.setUniversity(this);
//    }

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

//    public List<Student> getStudents() {
//        return students;
//    }
//
//    public void setStudents(List<Student> students) {
//        this.students = students;
//    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

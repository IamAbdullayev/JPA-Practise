package com.abdullayev.apps.universitySystem.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "university")
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

    @OneToMany(mappedBy = "university")
    private List<Student> students = new ArrayList<>();

//    @ManyToMany(cascade = CascadeType.PERSIST)
//    @JoinTable(
//            name = "teacher_uni",
//            joinColumns = @JoinColumn(name = "uni_id"),
//            inverseJoinColumns = @JoinColumn(name = "teacher_id")
//    )
//    private List<Teacher> teachers = new ArrayList<>();

    public University() {}

    public University(String name) {
        this.name = name;
    }

    public void addStudentToUniversity(Student newStudent) {
        students.add(newStudent);
        newStudent.setUniversity(this);
    }

//    public void addTeacherToUni(Teacher teacher) {
//        teachers.add(teacher);
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    //    public List<Teacher> getTeachers() {
//        return teachers;
//    }
//
//    public void setTeachers(List<Teacher> teachers) {
//        this.teachers = teachers;
//    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

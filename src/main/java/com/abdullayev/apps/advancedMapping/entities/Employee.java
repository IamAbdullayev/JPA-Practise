package com.abdullayev.apps.advancedMapping.entities;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "salary")
    private Double salary;

    @Embedded
    private Address address;

    @ElementCollection
    @CollectionTable(name = "emp_friend", joinColumns = @JoinColumn(name = "emp_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "friend_name")),
            @AttributeOverride(name = "surname", column = @Column(name = "friend_surname")),
            @AttributeOverride(name = "age", column = @Column(name = "friend_age"))
    })
    private List<Friend> friends = new ArrayList<>();

    public Employee() {
    }

    public Employee(String name, String surname, Double salary, Address address, List<Friend> friends) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.address = address;
        this.friends = friends;
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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", salary=" + salary +
                ", address=" + address +
                ", friends=" + friends +
                '}';
    }
}

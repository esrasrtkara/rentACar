package com.rentACar.rentACar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "gsm")
    private String gsm;
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Customer> customers;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Employee> employees;

}

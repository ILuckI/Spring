package com.lscode.studentDatabaseApp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "`student`")
public class Student{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String name;
    private String surname;
    private String email;
    private String address;
    private int age;
    private String password;

    @ManyToOne
    private SchoolClass schoolClass;
}

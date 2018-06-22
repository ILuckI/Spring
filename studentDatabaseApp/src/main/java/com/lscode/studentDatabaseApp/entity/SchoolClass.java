package com.lscode.studentDatabaseApp.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "`class`")
public class SchoolClass {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    Long id;
    private String className;
    private String tutor;

    @OneToMany
    private List<Student> students;
}

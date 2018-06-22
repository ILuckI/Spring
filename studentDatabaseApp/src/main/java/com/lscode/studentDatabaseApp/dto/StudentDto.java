package com.lscode.studentDatabaseApp.dto;

import lombok.Data;

@Data
public class StudentDto {
    Long id;
    private String name;
    private String surname;
    private String email;
    private String address;
    private int age;
    private String password;
    private String schoolClass;
}

package com.lscode.studentDatabaseApp.repository;


import com.lscode.studentDatabaseApp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findAllBySurname(String surname);

    Student findAllByEmail(String username);
}

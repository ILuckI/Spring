package com.lscode.studentDatabaseApp.service;

import com.lscode.studentDatabaseApp.dto.StudentDto;
import com.lscode.studentDatabaseApp.entity.SchoolClass;
import com.lscode.studentDatabaseApp.entity.Student;
import com.lscode.studentDatabaseApp.exception.UserExistException;
import com.lscode.studentDatabaseApp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final SchoolClassService schoolClassService;

    @Autowired
    public StudentService(StudentRepository studentRepository, SchoolClassService schoolClassService) {
        this.studentRepository = studentRepository;
        this.schoolClassService = schoolClassService;

    }

    private Student convertSaveStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setName(studentDto.getName());
        student.setSurname(studentDto.getSurname());
        student.setEmail(studentDto.getEmail());
        student.setAddress(studentDto.getAddress());
        student.setAge(studentDto.getAge());
        student.setPassword(studentDto.getPassword());
        return student;
    }

    private Student convertToEntity(StudentDto studentDto) {
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setName(studentDto.getName());
        student.setSurname(studentDto.getSurname());
        student.setEmail(studentDto.getEmail());
        student.setAddress(studentDto.getAddress());
        student.setAge(studentDto.getAge());
        return student;
    }

    private StudentDto convertToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setSurname(student.getSurname());
        studentDto.setEmail(student.getEmail());
        studentDto.setAddress(student.getAddress());
        studentDto.setAge(student.getAge());
        if (student.getSchoolClass() != null) {
            studentDto.setSchoolClass(student.getSchoolClass()
                    .getClassName());
        }
        return studentDto;
    }

    public StudentDto saveStudent(StudentDto studentDto) {
        Student student = convertSaveStudent(studentDto);
        Student saveStudent = studentRepository.save(student);
        StudentDto resultDto = convertToDto(saveStudent);
        return resultDto;
    }

    public Student getUserbySurname(String surname) {
        return studentRepository.findAllBySurname(surname);
    }


    public List<StudentDto> getAllStudents() {
        List<Student> allStudents = studentRepository.findAll();
        List<StudentDto> result = new ArrayList<>();
        for (Student student : allStudents) {
            result.add(convertToDto(student));

        }
        return result;
    }

    public StudentDto getStudent(String surname) {
        Student student = studentRepository.findAllBySurname(surname);
        return convertToDto(student);
    }

    public void addToClass(Long studentId, Long classId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            SchoolClass schoolClass = schoolClassService.getClassName(classId);
            Student student = optionalStudent.get();
            student.setSchoolClass(schoolClass);
            studentRepository.save(student);
        } else {
            throw new UserExistException();
        }
    }

    public Student getUserbyEmail(String username) {
        return studentRepository.findAllByEmail(username);
    }

    public void deleteStudent(Long studentId) {
        try {
            studentRepository.deleteById(studentId);
        } catch (EmptyResultDataAccessException e) {
            throw new UserExistException();
        }
    }
}

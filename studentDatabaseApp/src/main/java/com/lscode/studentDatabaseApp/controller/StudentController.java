package com.lscode.studentDatabaseApp.controller;

import com.lscode.studentDatabaseApp.dto.StudentDto;
import com.lscode.studentDatabaseApp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public void addStudent(@RequestBody StudentDto studentDto) {
        studentService.saveStudent(studentDto);
    }

    @PutMapping(value = "/{studentId}")
    public void updateStudent(@RequestBody StudentDto studentDto, @PathVariable Long studentId) {
        studentDto.setId(studentId);
        studentService.saveStudent(studentDto);
    }

    @DeleteMapping(value = "/{studentId}")
    public void deleteStudent(@RequestBody StudentDto studentDto, @PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
    }


    @PutMapping(value = "/{studentId}/class/{classId}")
    public void addToClass(@PathVariable Long studentId, @PathVariable Long classId) {
        studentService.addToClass(studentId, classId);
    }
//
//    @GetMapping("/student")
//    public List<StudentDto> getAllStudent() {
//        return studentService.getAllStudents();
//    }

    @GetMapping("/student")
    public String getAllStudent(Model model){
        List<StudentDto> allStudents = studentService.getAllStudents();
        model.addAttribute("allStudents", allStudents);
        return "studentlist";
    }

}

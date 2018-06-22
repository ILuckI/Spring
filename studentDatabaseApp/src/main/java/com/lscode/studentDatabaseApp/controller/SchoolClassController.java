package com.lscode.studentDatabaseApp.controller;

import com.lscode.studentDatabaseApp.dto.SchoolClassDto;
import com.lscode.studentDatabaseApp.service.SchoolClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/class")
public class SchoolClassController {

    private final SchoolClassService schoolClassService;

    @Autowired
    public SchoolClassController(SchoolClassService schoolClassService) {
        this.schoolClassService = schoolClassService;
    }

    @PostMapping
    public void addClass(@RequestBody SchoolClassDto schoolClassDto){
        schoolClassService.saveClass(schoolClassDto);
    }

    @GetMapping
    public List<SchoolClassDto> getAllClass(){
        return schoolClassService.getAllClass();
    }

    @PutMapping(value = "/{classId}")
    public void updateClass(@RequestBody SchoolClassDto schoolClassDto,@PathVariable Long classId){
        schoolClassDto.setId(classId);
        schoolClassService.saveClass(schoolClassDto);
    }

    @DeleteMapping(value = "/{classId}")
    public void deleteClass(@PathVariable Long classId){
        schoolClassService.deleteClass(classId);
    }

}

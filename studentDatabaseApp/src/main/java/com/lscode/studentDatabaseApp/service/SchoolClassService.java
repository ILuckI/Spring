package com.lscode.studentDatabaseApp.service;

import com.lscode.studentDatabaseApp.dto.SchoolClassDto;
import com.lscode.studentDatabaseApp.entity.SchoolClass;
import com.lscode.studentDatabaseApp.exception.ClassExistException;
import com.lscode.studentDatabaseApp.repository.SchoolClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SchoolClassService {

    private final SchoolClassRepository schoolClassRepository;

    @Autowired
    public SchoolClassService(SchoolClassRepository schoolClassRepository) {
        this.schoolClassRepository = schoolClassRepository;
    }

    public SchoolClassDto convertToDto(SchoolClass schoolClass) {
        SchoolClassDto schoolClassDto = new SchoolClassDto();
        schoolClassDto.setId(schoolClass.getId());
        schoolClassDto.setClassName(schoolClass.getClassName());
        schoolClassDto.setTutor(schoolClass.getTutor());
        return schoolClassDto;
    }

    public SchoolClass convertToEntity(SchoolClassDto schoolClassDto) {
        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setId(schoolClassDto.getId());
        schoolClass.setClassName(schoolClassDto.getClassName());
        schoolClass.setTutor(schoolClassDto.getTutor());
        return schoolClass;
    }


    public SchoolClassDto saveClass(SchoolClassDto schoolClassDto) {
        SchoolClass schoolClass = convertToEntity(schoolClassDto);
        SchoolClass saveClass = schoolClassRepository.save(schoolClass);
        return convertToDto(saveClass);
    }

    public List<SchoolClassDto> getAllClass() {
        List<SchoolClass> all = schoolClassRepository.findAll();
        List<SchoolClassDto> result = new ArrayList<>();
        for (SchoolClass sclass : all) {
            result.add(convertToDto(sclass));
        }
        return result;
    }

    public SchoolClass getClassName(Long classId) {
        Optional<SchoolClass> byId = schoolClassRepository.findById(classId);
        if (!byId.isPresent()) {
            throw new ClassExistException();
        }
        return byId.get();
    }

    public void deleteClass(Long classId){
        try {
            schoolClassRepository.deleteById(classId);
        } catch (EmptyResultDataAccessException e){
            throw  new  ClassExistException();
        }
    }



}

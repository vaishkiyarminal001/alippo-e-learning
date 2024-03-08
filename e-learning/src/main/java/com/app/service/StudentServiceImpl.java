package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.NotFoundException;
import com.app.exception.SomethingWentWrong;
import com.app.models.Student;
import com.app.repo.StudentRepository;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) throws SomethingWentWrong {
        try {
            return studentRepository.save(student);
        } catch (Exception ex) {
            throw new SomethingWentWrong("Error occurred while creating student");
        }
    }

    @Override
    public Student getStudentById(Long studentId) throws NotFoundException {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + studentId));
    }

    @Override
    public List<Student> getAllStudent() throws SomethingWentWrong {
        try {
            return studentRepository.findAll();
        } catch (Exception ex) {
            throw new SomethingWentWrong("Error occurred while retrieving students");
        }
    }

    @Override
    public Student deleteStudent(Long studentId) throws NotFoundException {
        Student student = getStudentById(studentId);
        studentRepository.delete(student);
        return student;
    }
}

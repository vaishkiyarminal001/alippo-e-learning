package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.exception.NotFoundException;
import com.app.exception.SomethingWentWrong;
import com.app.models.Student;

public interface StudentService {

	Student createStudent(Student student) throws SomethingWentWrong;

	Student getStudentById(Long studentId) throws NotFoundException;

	List<Student> getAllStudent() throws SomethingWentWrong;

	Student deleteStudent(Long studentId) throws NotFoundException;

	Optional<Student> findByEmail(String Email);

}

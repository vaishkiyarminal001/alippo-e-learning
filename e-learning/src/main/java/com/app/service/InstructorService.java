package com.app.service;

import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.app.exception.SomethingWentWrong;
import com.app.models.Course;
import com.app.models.Instructor;

public interface InstructorService {

	Instructor getInstructorById(Long instructorId) throws NotFoundException;

	Instructor registerInstructor(Instructor instructor) throws SomethingWentWrong;

	Instructor updateInstructor(Long instructorId, Instructor instructor) throws NotFoundException, SomethingWentWrong;

	Instructor deleteInstructorById(Long instructorId) throws NotFoundException;
	
	Course registerStudentCourse(Long instructorId, Long courseId, Long studentId) throws NotFoundException;
	
	 Optional<Instructor> findByEmail(String Email);

}

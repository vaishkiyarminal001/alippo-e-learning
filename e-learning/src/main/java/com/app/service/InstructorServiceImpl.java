package com.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.app.exception.SomethingWentWrong;
import com.app.models.Course;
import com.app.models.Instructor;
import com.app.models.Student;
import com.app.repo.CourseRepository;
import com.app.repo.InstructorRepository;
import com.app.repo.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class InstructorServiceImpl implements InstructorService{
	
	@Autowired
	private InstructorRepository instructorRepository; 
	
	@Autowired
	private CourseRepository courseRepository;
	
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public Instructor getInstructorById(Long instructorId) throws NotFoundException {
		Instructor instructor = instructorRepository.findById(instructorId).get();
		return instructor;
	}

	 @Override
	    public Instructor registerInstructor(Instructor instructor) throws SomethingWentWrong {
	        try {
	            return instructorRepository.save(instructor);
	        } catch (Exception ex) {
	            throw new SomethingWentWrong("Error occurred while registering instructor");
	        }
	    }

	 @Override
	 @Transactional
	 public Instructor updateInstructor(Long instructorId, Instructor updatedInstructor) throws NotFoundException, SomethingWentWrong {
	     try {
	         Instructor existingInstructor = getInstructorById(instructorId);

	         // Update the properties of the existing instructor with the provided values
	         existingInstructor.setName(updatedInstructor.getName());
	         existingInstructor.setEmail(updatedInstructor.getEmail());
	         existingInstructor.setPassword(updatedInstructor.getPassword());

	         // Save the updated instructor
	         return instructorRepository.save(existingInstructor);
	     } catch (NotFoundException ex) {
	         throw new NotFoundException();
	     } catch (Exception ex) {
	         throw new SomethingWentWrong("Error occurred while updating instructor");
	     }
	 }


	 @Override
	    public Instructor deleteInstructorById(Long instructorId) throws NotFoundException {
	        Instructor instructor = getInstructorById(instructorId);
	        instructorRepository.delete(instructor);
	        return instructor;
	    }
	 
	 
	 
	 
	 @Override
	 @Transactional
	 public Course registerStudentCourse(Long instructorId, Long courseId, Long studentId) throws NotFoundException {
	     try {
	         // Retrieve the instructor
	         Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new NotFoundException());
	         
	         // Retrieve the course
	         Course course = courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException());
	         
	         // Retrieve the student
	         Student student = studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException());

	         // Check if the student is already enrolled in the course
//	         if (course.getStudent().getStudentId() == studentId) {
//	             return "Student is already enrolled in this course";
//	         }

	         // Add the student to the course
	         course.setStudent(student);
	         
	         // Save the updated course
	      return   courseRepository.save(course);

//	         return "Student successfully registered for the course";
	     } catch (NotFoundException e) {
	         throw e;
//	     } catch (Exception e) {
//	         return "Error occurred while registering student for the course";
	     }
	 }

	 @Override
	 public Optional<Instructor> findByEmail(String Email) {
			Optional<Instructor> user= instructorRepository.findByEmail(Email);
			 if(user.isEmpty()) throw new SomethingWentWrong("No instructor found");
			 return user;
		}

	 }

	







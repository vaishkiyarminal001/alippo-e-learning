package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.SomethingWentWrong;
import com.app.models.Course;
import com.app.models.Student;
import com.app.service.CourseService;
import com.app.service.StudentService;

@RestController
@RequestMapping("/instructor")
public class InstructorController {
	
	@Autowired 
	private StudentService studentService;
	
	@Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        try {
            Course createdCourse = courseService.createCourse(course);
            return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
        } catch (SomethingWentWrong e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long courseId) throws NotFoundException {
        Course course = courseService.getCourseById(courseId);
		return new ResponseEntity<>(course, HttpStatus.OK);
    }

	
	 @GetMapping("/allStudent")
	    public ResponseEntity<Object> getAllStudents() {
	        try {
	            List<Student> students = studentService.getAllStudent();
	            return new ResponseEntity<>(students, HttpStatus.OK);
	        } catch (SomethingWentWrong e) {
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 
	 @GetMapping
	    public ResponseEntity<List<Course>> getAllCourses() {
	        try {
	            List<Course> courses = courseService.getAllCourses();
	            return new ResponseEntity<>(courses, HttpStatus.OK);
	        } catch (SomethingWentWrong e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @PutMapping("/{courseId}")
	    public ResponseEntity<Course> updateCourse(@RequestBody Course course, @PathVariable Long courseId) throws NotFoundException {
	        try {
	            Course updatedCourse = courseService.updateCourse(course, courseId);
	            return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
	        } catch (SomethingWentWrong e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @DeleteMapping("/{courseId}")
	    public ResponseEntity<Course> deleteCourse(@PathVariable Long courseId) throws NotFoundException {
	        Course deletedCourse = courseService.deleteCourse(courseId);
			return new ResponseEntity<>(deletedCourse, HttpStatus.OK);
	    }
	
	

}

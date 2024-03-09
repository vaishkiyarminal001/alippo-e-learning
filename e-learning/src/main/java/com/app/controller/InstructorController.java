package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.SomethingWentWrong;
import com.app.models.Certificate;
import com.app.models.ClassSession;
import com.app.models.Course;
import com.app.models.Instructor;
import com.app.models.Student;
import com.app.service.CertificateService;
import com.app.service.ClassSessionService;
import com.app.service.CourseService;
import com.app.service.InstructorService;
import com.app.service.StudentService;

@RestController
@RequestMapping("/instructor")
public class InstructorController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private InstructorService instructorService;

	@Autowired
	private CertificateService certificateService;

	@Autowired
	private ClassSessionService classSessionService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@PostMapping("/signin")
	public ResponseEntity<String> logInUserHandler(Authentication auth) throws SomethingWentWrong {
		Instructor admin = instructorService.findByEmail(auth.getName()).get();
		return new ResponseEntity<>(admin.getEmail() + " Logged In Successfully", HttpStatus.ACCEPTED);
	}

	@GetMapping("/getInstructorById/{instructorId}")
	public ResponseEntity<Instructor> getInstructorById(@PathVariable Long instructorId) throws NotFoundException {
		try {
			Instructor instructor = instructorService.getInstructorById(instructorId);
			return new ResponseEntity<>(instructor, HttpStatus.OK);
		} catch (SomethingWentWrong e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/registerInstructor")
	public ResponseEntity<Instructor> registerInstructor(@RequestBody Instructor instructor) {
		try {
			instructor.setPassword(passwordEncoder.encode(instructor.getPassword()));
			Instructor createdInstructor = instructorService.registerInstructor(instructor);
			return new ResponseEntity<>(createdInstructor, HttpStatus.CREATED);
		} catch (SomethingWentWrong e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updateInstructor/{instructorId}")
	public ResponseEntity<Instructor> updateInstructor(@PathVariable Long instructorId,
			@RequestBody Instructor updatedInstructor) throws NotFoundException {
		try {
			Instructor instructor = instructorService.updateInstructor(instructorId, updatedInstructor);
			return new ResponseEntity<>(instructor, HttpStatus.OK);
		} catch (SomethingWentWrong e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/deleteInstructorById/{instructorId}")
	public ResponseEntity<Instructor> deleteInstructorById(@PathVariable Long instructorId) throws NotFoundException {
		try {
			Instructor deletedInstructor = instructorService.deleteInstructorById(instructorId);
			return new ResponseEntity<>(deletedInstructor, HttpStatus.OK);
		} catch (SomethingWentWrong e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Instructor can add course

	@PostMapping("/registerCourse/{course}")
	public ResponseEntity<Course> createCourse(@RequestBody Course course, Long instructorId) {
		try {
			Course createdCourse = courseService.createCourse(course, instructorId);
			return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
		} catch (SomethingWentWrong e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Instructor can see the course accordng to the ID

	@GetMapping("/getCourseById/{courseId}")
	public ResponseEntity<Course> getCourseById(@PathVariable Long courseId) throws NotFoundException {
		Course course = courseService.getCourseById(courseId);
		return new ResponseEntity<>(course, HttpStatus.OK);
	}

	// Instructor can all the course

	@GetMapping("/allCourse")
	public ResponseEntity<List<Course>> getAllCourses() {
		try {
			List<Course> courses = courseService.getAllCourses();
			return new ResponseEntity<>(courses, HttpStatus.OK);
		} catch (SomethingWentWrong e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Instructor can see students

	@GetMapping("/getAllStudent")
	public ResponseEntity<Object> getAllStudents() {
		try {
			List<Student> students = studentService.getAllStudent();
			return new ResponseEntity<>(students, HttpStatus.OK);
		} catch (SomethingWentWrong e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Instructor can update anything in the course
	@PutMapping("/updateCourseById/{courseId}")
	public ResponseEntity<Course> updateCourse(@RequestBody Course course, @PathVariable Long courseId)
			throws NotFoundException {
		try {
			Course updatedCourse = courseService.updateCourse(course, courseId);
			return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
		} catch (SomethingWentWrong e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Instructor can delete course
	@DeleteMapping("/deleteCourseById/{courseId}")
	public ResponseEntity<Course> deleteCourse(@PathVariable Long courseId) throws NotFoundException {
		Course deletedCourse = courseService.deleteCourse(courseId);
		return new ResponseEntity<>(deletedCourse, HttpStatus.OK);
	}

	@PostMapping("/registerStudentCourse/{courseId}/{instructorId}/{studentId}")
	public ResponseEntity<?> registerStudentCourse(@PathVariable Long instructorId, @PathVariable Long courseId,
			@PathVariable Long studentId) {

		try {
			Course course = instructorService.registerStudentCourse(instructorId, courseId, studentId);
//	        	System.out.println(courseId);
//		    	System.out.println(instructorId);
//		    	System.out.println(studentId);
			return new ResponseEntity<>(course, HttpStatus.OK);

		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("Error occurred while registering student for the course",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/createClassSession")
	public ResponseEntity<?> createClassSession(@RequestBody ClassSession classSession, @RequestParam Long instructorId,
			@RequestParam Long studentId) {
		try {
			ClassSession createdClassSession = classSessionService.createClassSession(classSession, instructorId,
					studentId);
			return new ResponseEntity<>(createdClassSession, HttpStatus.CREATED);
		} catch (SomethingWentWrong e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getClassSessionById/{sessionId}")
	public ResponseEntity<?> getClassSessionById(@PathVariable Long sessionId) {
		ClassSession classSession = classSessionService.getClassSessionById(sessionId);
		return new ResponseEntity<>(classSession, HttpStatus.OK);
	}

	@GetMapping("/getAllClassSessions")
	public ResponseEntity<?> getAllClassSessions() {
		try {
			List<ClassSession> classSessions = classSessionService.getAllClassSessions();
			return new ResponseEntity<>(classSessions, HttpStatus.OK);
		} catch (SomethingWentWrong e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getAllClassSessions/course/{courseId}")
	public ResponseEntity<?> getAllClassSessions(@PathVariable Long courseId) {
		List<ClassSession> classSessions = classSessionService.getClassSessionsByCourse(courseId);
		return new ResponseEntity<>(classSessions, HttpStatus.OK);
	}

	@DeleteMapping("/deleteClassSessionById/{sessionId}")
	public ResponseEntity<?> deleteClassSession(@PathVariable Long sessionId, @RequestParam Long instructorId) {
		ClassSession deletedClassSession = classSessionService.deleteClassSession(sessionId, instructorId);
		return new ResponseEntity<>(deletedClassSession, HttpStatus.OK);
	}

	@PostMapping("/generateCertificate")
	public ResponseEntity<Certificate> generateCertificate(@RequestParam Long instructorId,
			@RequestParam Long studentId, @RequestParam Long courseId) {
		Certificate certificate = certificateService.generateCertificateForStudent(instructorId, studentId, courseId);
		return ResponseEntity.ok(certificate);
	}

}

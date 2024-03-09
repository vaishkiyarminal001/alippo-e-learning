package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.NotFoundException;
import com.app.exception.SomethingWentWrong;
import com.app.models.Certificate;
import com.app.models.ClassSession;
import com.app.models.Course;
import com.app.models.Instructor;
import com.app.models.Payment;
import com.app.models.Student;
import com.app.service.CertificateService;
import com.app.service.ClassSessionService;
import com.app.service.CourseService;
import com.app.service.PaymentService;
import com.app.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CertificateService certificateService;
    
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private PaymentService paymentService;
    
    @Autowired
    private ClassSessionService classSessionService;
    
	@Autowired
	private PasswordEncoder passwordEncoder;
    
    @PostMapping("/signin")
	public ResponseEntity<String> logInUserHandler(Authentication auth) throws SomethingWentWrong {
		Student student = studentService.findByEmail(auth.getName()).get();
		return new ResponseEntity<>(student.getEmail() + " Logged In Successfully", HttpStatus.ACCEPTED);
	}
    
    
    @PostMapping("/registerStudent")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        try {
        	student.setPassword(passwordEncoder.encode(student.getPassword()));
            Student createdStudent = studentService.createStudent(student);
            return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
        } catch (SomethingWentWrong e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getStudentById/{studentId}")
    public ResponseEntity<Object> getStudentById(@PathVariable Long studentId) {
        try {
            Student student = studentService.getStudentById(studentId);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/getCourseById/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long courseId) {
        try {
            Course course = courseService.getCourseById(courseId);
            return new ResponseEntity<>(course, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/deleteStudentById/{studentId}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Long studentId) {
        try {
            Student deletedStudent = studentService.deleteStudent(studentId);
            return new ResponseEntity<>(deletedStudent, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/makePayment/{userId}/{courseId}/{amount}")
    public ResponseEntity<Payment> makePayment(@PathVariable Long userId, @PathVariable Long courseId, @PathVariable double amount) {
        try {
            Payment payment = paymentService.makePayment(userId, courseId, amount);
            return new ResponseEntity<>(payment, HttpStatus.CREATED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/getPaymentByUser/user/{userId}")
    public ResponseEntity<List<Payment>> getPaymentsByUser(@PathVariable Long userId) {
        try {
            List<Payment> payments = paymentService.getPaymentsByUser(userId);
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

    @GetMapping("getPaymentsByCourseId/{courseId}")
    public ResponseEntity<List<Payment>> getPaymentsByCourse(@PathVariable Long courseId) {
        try {
            List<Payment> payments = paymentService.getPaymentsByCourse(courseId);
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

  
    @GetMapping("/getCertificateById/{userId}/{certificateId}")
    public ResponseEntity<Certificate> getCertificateById(@PathVariable Long userId,
                                                          @PathVariable Long certificateId) {
        try {
            Certificate certificate = certificateService.getCertificateById(userId, certificateId);
            return ResponseEntity.ok(certificate);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
}

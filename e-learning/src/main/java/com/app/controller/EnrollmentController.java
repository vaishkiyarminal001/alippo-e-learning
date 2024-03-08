package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.models.Enrollment;
import com.app.service.EnrollmentService;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;
    
    @PostMapping("/enroll")
    public ResponseEntity<Enrollment> enrollStudent(@RequestBody Enrollment enrollment) {
        Enrollment newEnrollment = enrollmentService.enrollStudent(enrollment);
        return new ResponseEntity<>(newEnrollment, HttpStatus.CREATED);
    }
}

package com.app.service;

import org.springframework.stereotype.Service;

import com.app.models.Enrollment;

@Service
public interface EnrollmentService {
   
    
    Enrollment enrollStudent(Enrollment enrollment);
}

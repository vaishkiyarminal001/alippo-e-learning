package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.models.Course;
import com.app.repo.CourseRepository;

@Service
public interface CourseService {
   
    
    Course createCourse(Course course);
    
    List<Course> getAllCourses();
    
}

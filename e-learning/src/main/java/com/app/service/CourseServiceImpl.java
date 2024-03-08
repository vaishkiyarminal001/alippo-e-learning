package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.models.Course;
import com.app.repo.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    
    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }
    
    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}

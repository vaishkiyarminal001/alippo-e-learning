package com.app.service;

import java.util.List;

import com.app.exception.NotFoundException;
import com.app.exception.SomethingWentWrong;
import com.app.models.Course;


public interface CourseService {

	Course createCourse(Course course) throws SomethingWentWrong;

	Course getCourseById(Long courseId) throws NotFoundException;

	List<Course> getAllCourses() throws SomethingWentWrong;
	
	Course updateCourse(Course course, Long courseId) throws NotFoundException, SomethingWentWrong; 

//	List<Course> getCoursesByCategory(String category) throws SomethingWentWrong;

	Course deleteCourse(Long courseId) throws NotFoundException;

}

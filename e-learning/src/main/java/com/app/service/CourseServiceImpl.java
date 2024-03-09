package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.NotFoundException;
import com.app.exception.SomethingWentWrong;
import com.app.models.Course;
import com.app.models.Instructor;
import com.app.repo.CourseRepository;

import jakarta.transaction.Transactional;

@Service
public class CourseServiceImpl implements CourseService {
    
    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private InstructorService instructorService;

    @Transactional
    @Override
    public Course createCourse(Course course, Long instructorId) throws SomethingWentWrong {
        try {
        	Instructor instructor = instructorService.getInstructorById(instructorId);
//        	System.out.println(instructorId);
//        	System.out.println(course.getCourseId());
        	course.setInstructor(instructor);
//        	System.out.println(course.getCourseId());
            return courseRepository.save(course);
        } catch (Exception ex) {
            throw new SomethingWentWrong("Error occurred while creating course");
        }
    }

    @Override
    public Course getCourseById(Long courseId) throws NotFoundException {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course not found with id: " + courseId));
    }

    @Override
    public List<Course> getAllCourses() throws SomethingWentWrong {
        try {
            return courseRepository.findAll();
        } catch (Exception ex) {
            throw new SomethingWentWrong("Error occurred while retrieving courses");
        }
    }


    @Override
    public Course deleteCourse(Long courseId) throws NotFoundException {
        Course course = getCourseById(courseId);
        courseRepository.delete(course);
        return course;
    }

    @Transactional
    @Override
    public Course updateCourse(Course course, Long courseId) throws NotFoundException, SomethingWentWrong {
        try {
            Course existingCourse = getCourseById(courseId);

            // Update the properties of the existing course with the provided values
            existingCourse.setCourseName(course.getCourseName());
            existingCourse.setDescription(course.getDescription());
            existingCourse.setInstructor(course.getInstructor());
            existingCourse.setPrice(course.getPrice());

            // Save the updated course
            return courseRepository.save(existingCourse);
        } catch (NotFoundException ex) {
            throw new NotFoundException("Course not found with id: " + courseId);
        } catch (Exception ex) {
            throw new SomethingWentWrong("Error occurred while updating course");
        }
    }

}

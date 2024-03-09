package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.NotFoundException;
import com.app.exception.SomethingWentWrong;
import com.app.models.ClassSession;
import com.app.models.Instructor;
import com.app.models.Student;
import com.app.repo.ClassSessionRepository;
import com.app.repo.CourseRepository;
import com.app.repo.InstructorRepository;
import com.app.repo.StudentRepository;

@Service
public class ClassSessionServiceImpl implements ClassSessionService {


    @Autowired
    private ClassSessionRepository classSessionRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public ClassSession createClassSession(ClassSession classSession, Long instructorId, Long studentId)
            throws SomethingWentWrong, NotFoundException {
        try {
            Instructor instructor = instructorRepository.findById(instructorId)
                    .orElseThrow(() -> new NotFoundException("Instructor not found with ID: " + instructorId));
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new NotFoundException("Student not found with ID: " + studentId));
            classSession.setInstructor(instructor);
            classSession.setStudent(student);
            return classSessionRepository.save(classSession);
        } catch (Exception ex) {
            throw new SomethingWentWrong("Error occurred while creating class session");
        }
    }

    @Override
    public ClassSession getClassSessionById(Long sessionId) throws NotFoundException {
        return classSessionRepository.findById(sessionId)
                .orElseThrow(() -> new NotFoundException("Class session not found with ID: " + sessionId));
    }

    @Override
    public List<ClassSession> getAllClassSessions() throws SomethingWentWrong {
        try {
            return classSessionRepository.findAll();
        } catch (Exception ex) {
            throw new SomethingWentWrong("Error occurred while retrieving class sessions");
        }
    }

    @Override
    public List<ClassSession> getClassSessionsByCourse(Long courseId) throws NotFoundException {
        List<ClassSession> classSessions = classSessionRepository.findByCourse_CourseId(courseId);
        if (classSessions.isEmpty()) {
            throw new NotFoundException("No class sessions found for course with ID: " + courseId);
        }
        return classSessions;
    }

    @Override
    public ClassSession deleteClassSession(Long sessionId, Long instructorId) throws NotFoundException {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new NotFoundException("Instructor not found with ID: " + instructorId));
        ClassSession classSession = classSessionRepository.findById(sessionId)
                .orElseThrow(() -> new NotFoundException("Class session not found with ID: " + sessionId));
        if (!classSession.getInstructor().equals(instructor)) {
            throw new NotFoundException("Class session not found for the provided instructor");
        }
        classSessionRepository.delete(classSession);
        return classSession;
    }

}

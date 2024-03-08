package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.NotFoundException;
import com.app.exception.SomethingWentWrong;
import com.app.models.ClassSession;
import com.app.repo.ClassSessionRepository;
import com.app.repo.CourseRepository;

import java.util.List;

@Service
public class ClassSessionServiceImpl implements ClassSessionService {

    @Autowired
    private ClassSessionRepository classSessionRepository;
    
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public ClassSession createClassSession(ClassSession classSession) throws SomethingWentWrong {
        try {
            return classSessionRepository.save(classSession);
        } catch (Exception ex) {
            throw new SomethingWentWrong("Error occurred while creating class session");
        }
    }

    @Override
    public ClassSession getClassSessionById(Long sessionId) throws NotFoundException {
        return classSessionRepository.findById(sessionId)
                .orElseThrow(() -> new NotFoundException("Class session not found with id: " + sessionId));
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
        List<ClassSession> classSessions = courseRepository.findByCourseId(courseId);
        if (classSessions.isEmpty()) {
            throw new NotFoundException("No class sessions found for course with ID: " + courseId);
        }
        return classSessions;
    }

    @Override
    public ClassSession deleteClassSession(Long sessionId) throws NotFoundException {
        ClassSession classSession = getClassSessionById(sessionId);
        classSessionRepository.delete(classSession);
        return classSession;
    }
}

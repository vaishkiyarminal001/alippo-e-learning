package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.models.ClassSession;
import com.app.repo.ClassSessionRepository;

@Service
public class ClassSessionServiceImpl implements ClassSessionService {
    @Autowired
    private ClassSessionRepository classSessionRepository;
    
    @Override
    public ClassSession createClassSession(ClassSession classSession) {
        return classSessionRepository.save(classSession);
    }
    
    @Override
    public List<ClassSession> getAllClassSessions() {
        return classSessionRepository.findAll();
    }
}

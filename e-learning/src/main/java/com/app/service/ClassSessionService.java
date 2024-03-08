package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.models.ClassSession;
import com.app.repo.ClassSessionRepository;

@Service
public interface ClassSessionService {
    
    
    ClassSession createClassSession(ClassSession classSession);
    
    List<ClassSession> getAllClassSessions();
    
}

package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.models.ClassSession;
import com.app.service.ClassSessionService;

@RestController
@RequestMapping("/class-sessions")
public class ClassSessionController {
    @Autowired
    private ClassSessionService classSessionService;
    
    @PostMapping("/create")
    public ResponseEntity<ClassSession> createClassSession(@RequestBody ClassSession classSession) {
        ClassSession newClassSession = classSessionService.createClassSession(classSession);
        return new ResponseEntity<>(newClassSession, HttpStatus.CREATED);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<ClassSession>> getAllClassSessions() {
        List<ClassSession> classSessions = classSessionService.getAllClassSessions();
        return new ResponseEntity<>(classSessions, HttpStatus.OK);
    }
}

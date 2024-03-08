package com.app.service;

import java.util.List;

import com.app.exception.NotFoundException;
import com.app.exception.SomethingWentWrong;
import com.app.models.ClassSession;


public interface ClassSessionService {
	ClassSession createClassSession(ClassSession classSession) throws SomethingWentWrong;

	ClassSession getClassSessionById(Long sessionId) throws NotFoundException;

	List<ClassSession> getAllClassSessions() throws SomethingWentWrong;

	List<ClassSession> getClassSessionsByCourse(Long courseId) throws NotFoundException;

	ClassSession deleteClassSession(Long sessionId) throws NotFoundException;
}

package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.models.ClassSession;
@Repository
public interface ClassSessionRepository extends JpaRepository<ClassSession, Long>{

	List<ClassSession> findByCourse_CourseId(Long courseId);

}

package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.models.Enrollment;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{

}

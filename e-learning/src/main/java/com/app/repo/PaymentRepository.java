package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.models.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	@Query("SELECT p FROM Payment p WHERE p.student.studentId = :studentId")
    List<Payment> findByStudentId(Long studentId);

    @Query("SELECT p FROM Payment p WHERE p.course.courseId = :courseId")
    List<Payment> findByCourseId(Long courseId);

}

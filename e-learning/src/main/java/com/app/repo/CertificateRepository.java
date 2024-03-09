package com.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.models.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
	

	 @Query("SELECT c FROM Certificate c WHERE c.student.studentId = ?1 AND c.certificateId = ?2")
	    Optional<Certificate> findByStudentIdAndCertificateId(Long studentId, Long certificateId);


}

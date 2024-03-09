package com.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.models.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long>{

	Optional<Instructor> findByEmail(String email);

}

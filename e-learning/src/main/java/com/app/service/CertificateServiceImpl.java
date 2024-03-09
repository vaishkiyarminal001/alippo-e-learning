package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.NotFoundException;
import com.app.models.Certificate;
import com.app.models.Course;
import com.app.models.Instructor;
import com.app.models.Student;
import com.app.repo.CertificateRepository;
import com.app.repo.CourseRepository;
import com.app.repo.InstructorRepository;
import com.app.repo.StudentRepository;

@Service
public class CertificateServiceImpl implements CertificateService {

	 @Autowired
	    private CertificateRepository certificateRepository;

	    @Autowired
	    private CourseRepository courseRepository;

	    @Autowired
	    private StudentRepository studentRepository;

	    @Autowired
	    private InstructorRepository instructorRepository;

	    @Override
	    public Certificate generateCertificateForStudent(Long instructorId, Long studentId, Long courseId) throws NotFoundException {
	        Course course = courseRepository.findById(courseId)
	                .orElseThrow(() -> new NotFoundException("Course not found with ID: " + courseId));

	        Student student = studentRepository.findById(studentId)
	                .orElseThrow(() -> new NotFoundException("Student not found with ID: " + studentId));

	        Instructor instructor = instructorRepository.findById(instructorId)
	                .orElseThrow(() -> new NotFoundException("Instructor not found with ID: " + instructorId));

	        Certificate certificate = new Certificate();
	        certificate.setCourse(course);
	        certificate.setStudent(student);
	        certificate.setInstructor(instructor);

	        return certificateRepository.save(certificate);
	    }

	    @Override
	    public Certificate getCertificateById(Long userId, Long certificateId) throws NotFoundException {
	        Certificate certificate = certificateRepository.findByStudentIdAndCertificateId(userId, certificateId)
	                .orElseThrow(() -> new NotFoundException("Certificate not found with ID: " + certificateId));
	        return certificate;
	    }
	
}

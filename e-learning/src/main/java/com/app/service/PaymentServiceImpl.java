package com.app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.NotFoundException;
import com.app.models.Course;
import com.app.models.Payment;
import com.app.models.Student;
import com.app.repo.PaymentRepository;

import jakarta.transaction.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService {
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private CourseService courseService;

    @Transactional
    @Override
    public Payment makePayment(Long userId, Long courseId, double amount) throws NotFoundException {
        Payment payment = new Payment();
        
        // Assuming you have methods to retrieve Student and Course objects by their IDs
        Student student = studentService.getStudentById(userId);
        Course course = courseService.getCourseById(courseId);

        if (student == null || course == null) {
            throw new NotFoundException("Student or course not found");
        }

        payment.setStudent(student);
        payment.setCourse(course);
        payment.setAmount(amount);
        payment.setPaymentDate(LocalDateTime.now());

        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getPaymentsByUser(Long userId) throws NotFoundException {
        // method in PaymentRepository to find payments by user ID
        return paymentRepository.findByStudentId(userId);
    }

    @Override
    public List<Payment> getPaymentsByCourse(Long courseId) throws NotFoundException {
        // method in PaymentRepository to find payments by course ID
        return paymentRepository.findByCourseId(courseId);
    }
}

package com.app.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @NotNull(message = "Student is required")
    @ManyToOne
    @JoinColumn(name = "studentId")
    @JsonIgnore
    private Student student;

    @NotNull(message = "Course is required")
    @ManyToOne
    @JoinColumn(name = "courseId")
    @JsonIgnore
    private Course course;

    @NotNull(message = "Amount is required")
    private double amount;

    @NotNull(message = "Payment date is required")
    private LocalDateTime paymentDate;
}

package com.app.models;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "classSession")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassSession {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long classSessionId;

	@ManyToOne
	@JoinColumn(name = "course_id")
	@JsonIgnore
	private Course course;
	
	@ManyToOne
	@JoinColumn(name = "instructor_id")
	@JsonIgnore
	private Instructor instructor;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	@JsonIgnore
	private Student student;

	
	private LocalDate date;

	
	private String time;

	@NotBlank(message = "Topic is required")
	private String topic;

	private String zoomLink;

	
}

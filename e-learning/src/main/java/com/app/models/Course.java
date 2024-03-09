package com.app.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long courseId;

	@NotBlank(message = "Course name is required")
	private String courseName;

	@NotBlank(message = "Description is required")
	private String description;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "instructor_id")
	private Instructor instructor;

	private double price;
 
	@JsonIgnore
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ClassSession> classSessions;

}

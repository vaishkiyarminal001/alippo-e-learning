package com.app.models;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "classSession")
@Getter
@Setter
public class ClassSession {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sessionId;
	
	@ManyToOne
	@JoinColumn(name = "courseId")
	private Course course;
	
	private LocalDate date;
	
	private LocalTime time;
	
	private String topic;
	
	private String zoomLink;

}

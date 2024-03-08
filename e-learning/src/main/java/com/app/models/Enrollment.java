//package com.app.models;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotNull;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Table(name = "enrollments")
//@Getter
//@Setter
//public class Enrollment {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long enrollmentId;
//	
//	@NotNull(message = "User is required")
//    @ManyToOne
//    @JoinColumn(name = "userId")
//	@JsonIgnore
//    private MyUser user;
//    
//    @NotNull(message = "Course is required")
//    @ManyToOne
//    @JoinColumn(name = "courseId")
//    @JsonIgnore
//    private Course course;
//	
//
//}

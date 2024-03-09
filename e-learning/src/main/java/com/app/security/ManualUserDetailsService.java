package com.app.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.models.Instructor;
import com.app.models.Student;
import com.app.repo.InstructorRepository;
import com.app.repo.StudentRepository;


@Service
public class ManualUserDetailsService implements UserDetailsService {
    
	@Autowired
	 private StudentRepository studentRepository;
	
	@Autowired
	private InstructorRepository instructorRepository;
	
	public boolean isInsstructor(String email) {
	   Optional<Instructor> instructor = instructorRepository.findByEmail(email);
		if(instructor.isPresent()) return true;
		else return false;
	}
	
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		if(isInsstructor(email)) {
			Optional<Instructor> admin = instructorRepository.findByEmail(email);
			 
			 if(admin.isEmpty()) throw new UsernameNotFoundException("Admin not found");
			 Instructor us = admin.get();
			
			 
			List<GrantedAuthority> authorities = new ArrayList<>() ;
			SimpleGrantedAuthority autho = new SimpleGrantedAuthority("ROLE_"+us.getRole()) ;
			authorities.add(autho) ;
			User secUser = new User(us.getEmail(), us.getPassword(),  authorities) ;
			return secUser ;
		}else {
			
			Optional<Student> myUser = studentRepository.findByEmail(email);
				 
				 if(myUser.isEmpty()) throw new UsernameNotFoundException("User not found");
				 Student us = myUser.get();
				 				
				 
				List<GrantedAuthority> authorities = new ArrayList<>() ;
				SimpleGrantedAuthority autho = new SimpleGrantedAuthority("ROLE_"+us.getRole()) ;
				authorities.add(autho) ;
				User secUser = new User(us.getEmail(), us.getPassword(),  authorities) ;
				return secUser ;

				
			}
		}


}

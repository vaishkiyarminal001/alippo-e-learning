package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.models.MyUser;
import com.app.repo.UserRepository;

@Service
public interface UserService {
    
    MyUser createUser(MyUser user);
    
    List<MyUser> getAllUsers();

}

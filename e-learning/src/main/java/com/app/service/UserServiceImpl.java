package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.models.MyUser;
import com.app.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public MyUser createUser(MyUser user) {
        return userRepository.save(user);
    }
    
    @Override
    public List<MyUser> getAllUsers() {
        return userRepository.findAll();
    }
}

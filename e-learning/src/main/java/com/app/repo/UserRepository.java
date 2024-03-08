package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.models.MyUser;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Long>{

}

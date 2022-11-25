package com.example.fgocalculation.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fgocalculation.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
    
    //emailを元に検索
    User findByEmail(String email);
}

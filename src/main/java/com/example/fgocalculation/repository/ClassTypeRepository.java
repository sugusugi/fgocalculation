package com.example.fgocalculation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fgocalculation.entity.ClassType;

@Repository
public interface ClassTypeRepository extends JpaRepository<ClassType,String>{
    
    ClassType findByClassTypeName(String classTypeName);
}

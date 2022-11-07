package com.example.fgocalculation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fgocalculation.entity.ServantList;

@Repository
public interface ServantListRepository extends JpaRepository<ServantList, String> {
    
    ServantList findByServantName(String servantName);
}

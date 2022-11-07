package com.example.fgocalculation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fgocalculation.entity.CardType;

@Repository
public interface CardTypeRepository extends JpaRepository<CardType, String>{
    
    CardType findByCardTypeName(String cardTypeName);
}

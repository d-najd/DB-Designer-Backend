package com.umldesigner.schema.foreign_key.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umldesigner.schema.foreign_key.domain.SFK;

@Repository
public interface SFKRepository extends JpaRepository<SFK, String> {
    
}
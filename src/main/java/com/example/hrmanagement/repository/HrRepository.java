package com.example.hrmanagement.repository;

import com.example.hrmanagement.model.Hr;
import com.example.hrmanagement.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HrRepository extends JpaRepository<Hr,Integer> {
    Optional<Hr> findByUserId(int id);

}

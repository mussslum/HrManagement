package com.example.hrmanagement.repository;

import com.example.hrmanagement.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill,Integer> {
    Optional<Skill> findByName (String skillName);
}

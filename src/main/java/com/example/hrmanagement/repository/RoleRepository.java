package com.example.hrmanagement.repository;

import com.example.hrmanagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByRolename(String rolename);
}

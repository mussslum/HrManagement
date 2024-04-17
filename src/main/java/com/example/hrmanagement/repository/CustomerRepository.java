package com.example.hrmanagement.repository;

import com.example.hrmanagement.model.Customer;
import com.example.hrmanagement.model.Hr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByUserId(int id);
    List<Customer> findByVacancies_Id(int id);
}

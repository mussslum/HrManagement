package com.example.hrmanagement.repository;

import com.example.hrmanagement.model.Hr;
import com.example.hrmanagement.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VacancyRepository extends JpaRepository<Vacancy,Integer> {
    public List<Vacancy> findByHr(Hr hr);
}

package com.example.hrmanagement.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
@Table(name = "Vacancies")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String requirement;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hr_id")
    private Hr hr ;
    @ManyToMany(mappedBy = "vacancies")
    private List<Customer> customers;
}

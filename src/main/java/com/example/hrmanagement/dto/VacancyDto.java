package com.example.hrmanagement.dto;

import com.example.hrmanagement.model.Hr;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VacancyDto {
    private int id;
    private String title;
    private String description;
    private String requirement;
    private Hr hr;
}

package com.example.hrmanagement.service;

import com.example.hrmanagement.dto.UserEntityDto;
import com.example.hrmanagement.dto.VacancyDto;
import com.example.hrmanagement.model.Hr;

import java.util.List;

public interface VacancyService {
    public void createVacancy (VacancyDto vacancyDto);
    public List<VacancyDto> findAllVacancy ();
    public VacancyDto findVacancyById (int id);
    public void updateVacancy(VacancyDto vacancyDto,int id);
    public void deleteVacancy (int id);
    public List<VacancyDto> findAllVacancyByHr (Hr hr);
}

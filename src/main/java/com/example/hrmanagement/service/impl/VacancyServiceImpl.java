package com.example.hrmanagement.service.impl;

import com.example.hrmanagement.dto.UserEntityDto;
import com.example.hrmanagement.dto.VacancyDto;
import com.example.hrmanagement.model.Hr;
import com.example.hrmanagement.model.Vacancy;
import com.example.hrmanagement.repository.VacancyRepository;
import com.example.hrmanagement.service.VacancyService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class VacancyServiceImpl implements VacancyService {
    @Autowired
    private VacancyRepository vacancyRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public void createVacancy(VacancyDto vacancyDto) {
        Vacancy vacancy = modelMapper.map(vacancyDto,Vacancy.class);
        vacancyRepository.save(vacancy);
    }
    @Override
    public List<VacancyDto> findAllVacancy() {
        List<Vacancy> vacancies = vacancyRepository.findAll();
        List<VacancyDto> vacancyDtos =modelMapper.map(vacancies,new TypeToken<ArrayList<VacancyDto>>(){}.getType());
        return vacancyDtos;
    }
    @Override
    public VacancyDto findVacancyById(int id) {
        Vacancy vacancy=vacancyRepository.findById(id).get();
        VacancyDto vacancyDto = modelMapper.map(vacancy,VacancyDto.class);
        return vacancyDto;
    }

    @Override
    public void updateVacancy(VacancyDto vacancyDto, int id) {
        Vacancy vacancy = vacancyRepository.findById(id).get();
        vacancy.setTitle(vacancyDto.getTitle());
        vacancy.setDescription(vacancyDto.getDescription());
        vacancy.setRequirement(vacancyDto.getRequirement());
    }

    @Override
    public void deleteVacancy(int id) {
        Vacancy vacancy= vacancyRepository.findById(id).get();
        if(vacancy!=null)
        {
            vacancyRepository.deleteById(vacancy.getId());
        }
    }

    @Override
    public List<VacancyDto> findAllVacancyByHr(Hr hr) {
        List<Vacancy> vacancies = vacancyRepository.findByHr(hr);
        List<VacancyDto> vacancyDtos =modelMapper.map(vacancies,new TypeToken<ArrayList<VacancyDto>>(){}.getType());
        return vacancyDtos;
    }
}

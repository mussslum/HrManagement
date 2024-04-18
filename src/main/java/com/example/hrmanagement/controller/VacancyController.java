package com.example.hrmanagement.controller;

import com.example.hrmanagement.dto.VacancyDto;
import com.example.hrmanagement.model.*;
import com.example.hrmanagement.repository.CustomerRepository;
import com.example.hrmanagement.repository.HrRepository;
import com.example.hrmanagement.repository.UserRepository;
import com.example.hrmanagement.service.UserService;
import com.example.hrmanagement.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("vacancy")
public class VacancyController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private VacancyService vacancyService;
    @Autowired
    private HrRepository hrRepository;
    @Autowired
    private CustomerRepository customerRepository;
    public Hr getCurrentHr()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        UserEntity user=userRepository.findByUsername(name).get();
        Hr hr=hrRepository.findByUserId(user.getId()).get();
        return hr;
    }
    @GetMapping("all")
    public String main(ModelMap modelMap)
    {
        List<VacancyDto> vacancyDtos=vacancyService.findAllVacancyByHr(getCurrentHr());
        modelMap.addAttribute("vacancyDtos",vacancyDtos);
        return "allVacancies";
    }
    @GetMapping("create")
    public String getVacancy(ModelMap modelMap)
    {
        VacancyDto vacancyDto = new VacancyDto();
        modelMap.addAttribute("vacancyDto",vacancyDto);
        return "createVacancy";
    }
    @PostMapping("create")
    public String postVacancy(@ModelAttribute("vacancyDto") VacancyDto vacancyDto)
    {
        vacancyDto.setHr(getCurrentHr());
        vacancyService.createVacancy(vacancyDto);
        return "redirect:/vacancy/all";
    }
    @GetMapping("delete/{id}")
    public String deleteVacancy(@PathVariable("id") int id)
    {
        vacancyService.deleteVacancy(id);
        return "redirect:/vacancy/all";
    }
    @GetMapping("applicants/{id}")
    public String getApplicants(@PathVariable("id") int id ,ModelMap modelMap)
    {
        List<Customer> customers= customerRepository.findByVacancies_Id(id);
        modelMap.addAttribute("customers",customers);
        return "applicants";
    }

}

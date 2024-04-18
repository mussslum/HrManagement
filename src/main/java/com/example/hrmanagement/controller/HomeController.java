package com.example.hrmanagement.controller;

import com.example.hrmanagement.dto.UserEntityDto;
import com.example.hrmanagement.dto.VacancyDto;
import com.example.hrmanagement.model.Customer;
import com.example.hrmanagement.model.Hr;
import com.example.hrmanagement.model.UserEntity;
import com.example.hrmanagement.model.Vacancy;
import com.example.hrmanagement.repository.CustomerRepository;
import com.example.hrmanagement.repository.HrRepository;
import com.example.hrmanagement.repository.UserRepository;
import com.example.hrmanagement.repository.VacancyRepository;
import com.example.hrmanagement.service.UserService;
import com.example.hrmanagement.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("home")
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private VacancyService vacancyService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HrRepository hrRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private VacancyRepository vacancyRepository;
    public Customer getCurrentCustomer()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        UserEntity user=userRepository.findByUsername(name).get();
        Customer customer= customerRepository.findByUserId(user.getId()).get();
        return customer;
    }
    @GetMapping("/main")
    public String home(ModelMap modelMap)
    {
        int hrId=15;
        Hr hr= hrRepository.findById(15).get();
        List<VacancyDto> vacancyDtos=vacancyService.findAllVacancyByHr(hr);
        modelMap.addAttribute("vacancyDtos",vacancyDtos);
        return "home";
    }
    @GetMapping("/video")
    public String video(ModelMap modelMap)
    {
        /*
        Test ucun var bu controller
         */
        return "video";
    }
    @GetMapping("apply/{id}")
    public String apply(@PathVariable("id") int id)
    {
        Vacancy vacancy= vacancyRepository.findById(id).get();
        Customer customer=getCurrentCustomer();
        if(customer.getVacancies().contains(vacancy))
        {
            return "redirect:/home/main";
        }
        customer.getVacancies().add(vacancy);
        customerRepository.save(customer);
        return "redirect:/home/main";
    }
}

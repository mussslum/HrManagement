package com.example.hrmanagement.controller;

import com.example.hrmanagement.dto.UserEntityDto;
import com.example.hrmanagement.model.*;
import com.example.hrmanagement.repository.*;
import com.example.hrmanagement.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class AuthController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private SkillRepository skillRepository;;
    @Autowired
    private UserService userService;
    @Autowired
    private HrRepository hrRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @GetMapping("/login")
    public String login ()
    {
        return "login";
    }
    @GetMapping("/register")
    public String registerGet(ModelMap modelMap)
    {
        UserEntityDto userDto = new UserEntityDto();
        modelMap.addAttribute("userDto",userDto);
        List<Skill> skills = skillRepository.findAll();
        modelMap.addAttribute("skills",skills);
        return "register";
    }
    @PostMapping("/register")
    public String registerPost(@ModelAttribute("userDto") UserEntityDto userDto)
    {
        if(userService.existsOrNotByUsername(userDto.getUsername()))
        {
            return "redirect:register?error";
        }
        String roleName="user";
        Role role = roleRepository.findByRolename(roleName).get();
        userDto.setRoles(Collections.singletonList(role));
        userService.createUser(userDto);
        UserEntity user = userRepository.findByUsername(userDto.getUsername()).get();
        if(roleName=="hr")
        {
            Hr hr = new Hr();
            hr.setUser(user);
            hrRepository.save(hr);
        }
        if(roleName=="user")
        {
            Customer customer = new Customer();
            customer.setUser(user);
            customer.setSkill(skillRepository.findById(userDto.getSkillId()).get());
            customerRepository.save(customer);

        }
        return "redirect:/login";
    }
}

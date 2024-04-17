package com.example.hrmanagement.dto;

import com.example.hrmanagement.model.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserEntityDto {
    private String name;
    private String surname;
    private String username;
    private String password;
    private List<Role> roles = new ArrayList<>();
    private int skillId;
}

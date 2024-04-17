package com.example.hrmanagement.service;

import com.example.hrmanagement.dto.UserEntityDto;
import com.example.hrmanagement.model.UserEntity;

import java.util.List;

public interface UserService {
    public void createUser (UserEntityDto userEntityDto);
    public List<UserEntityDto> findAllUser ();
    public UserEntityDto findUserById (int id);
    public void updateUser(UserEntityDto userEntityDto,int id);
    public void deleteUser (int id);
    public UserEntityDto findUserByUsername(String username);
    Boolean existsOrNotByUsername (String Username);
}

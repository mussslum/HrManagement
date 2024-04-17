package com.example.hrmanagement.service.impl;

import com.example.hrmanagement.dto.UserEntityDto;
import com.example.hrmanagement.model.UserEntity;
import com.example.hrmanagement.repository.UserRepository;
import com.example.hrmanagement.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void createUser(UserEntityDto userEntityDto) {
        /*if(userRepository.existsByUsername(userEntityDto.getUsername()))
        {

        }*/
        userEntityDto.setPassword(passwordEncoder.encode(userEntityDto.getPassword()));
        UserEntity user =modelMapper.map(userEntityDto,UserEntity.class);
        userRepository.save(user);
    }

    @Override
    public List<UserEntityDto> findAllUser() {
        List<UserEntity> users=userRepository.findAll();
        List<UserEntityDto> userDtos= modelMapper.map(users,new TypeToken<ArrayList<UserEntityDto>>(){}.getType());
        return userDtos;
    }

    @Override
    public UserEntityDto findUserById(int id) {
        UserEntity user = userRepository.findById(id).get();
        UserEntityDto userDto = modelMapper.map(user,UserEntityDto.class);
        return userDto;
    }

    @Override
    public void updateUser(UserEntityDto userEntityDto, int id) {
        UserEntity user = userRepository.findById(id).get();
        user.setName(userEntityDto.getName());
        user.setSurname(userEntityDto.getSurname());
        user.setUsername(userEntityDto.getUsername());
        user.setPassword(userEntityDto.getPassword());
        /*user=modelMapper.map(userEntityDto,UserEntity.class);*/
        userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserEntityDto findUserByUsername(String username) {
        UserEntity user= userRepository.findByUsername(username).get();
        UserEntityDto userEntityDto=modelMapper.map(user,UserEntityDto.class);
        return userEntityDto;
    }

    @Override
    public Boolean existsOrNotByUsername(String username) {
        if(userRepository.existsByUsername(username))
        {
            return true;
        }
        return false;
    }
}

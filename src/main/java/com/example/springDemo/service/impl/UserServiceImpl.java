package com.example.springDemo.service.impl;

import com.example.springDemo.dto.MyRequestDTO;
import com.example.springDemo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserServiceImpl implements UserService {

    public UserServiceImpl() {
        System.out.println("inside user service construct");
    }

    @PostConstruct
    public void init(){
        System.out.println("inside user service post construct");
    }

    @Override
    public boolean updateEmployee(String id, MyRequestDTO request) {
        System.out.println("User Service : " + request + " " + id);
        return false;
    }
}

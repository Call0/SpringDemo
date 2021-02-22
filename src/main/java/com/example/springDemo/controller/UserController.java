package com.example.springDemo.controller;

import com.example.springDemo.dto.MyRequestDTO;
import com.example.springDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class UserController {
    // [POST] /update/employee/{id}
    // {"firstName" : " /// " } MyRequestDTO
    // boolean true or false


    public UserController(UserService userService) {
        this.userService = userService;
        System.out.println("inside user controller constructor");
    }

    @PostConstruct
    public void init(){
        System.out.println("inside controller post construct...");
    }

    @Autowired
    private UserService userService;


    @PostMapping(path="/update/employee/{id}")
    public boolean updateEmployee(@PathVariable String id, @RequestBody MyRequestDTO request){
        return userService.updateEmployee(id, request);
    }
}

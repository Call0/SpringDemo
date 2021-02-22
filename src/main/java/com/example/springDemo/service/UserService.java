package com.example.springDemo.service;

import com.example.springDemo.dto.MyRequestDTO;

public interface UserService {
    boolean updateEmployee(String id, MyRequestDTO request);
}

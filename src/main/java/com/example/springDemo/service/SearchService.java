package com.example.springDemo.service;


import com.example.springDemo.dto.SearchRequestDTO;
import com.example.springDemo.dto.SearchResponseDTO;

public interface SearchService {
    SearchResponseDTO productSearch(SearchRequestDTO request);
}

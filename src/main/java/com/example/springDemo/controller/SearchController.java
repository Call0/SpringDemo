package com.example.springDemo.controller;

import com.example.springDemo.dto.SearchRequestDTO;
import com.example.springDemo.dto.SearchResponseDTO;
import com.example.springDemo.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping(path = "/search")
    public SearchResponseDTO search(@RequestBody SearchRequestDTO request){
        return searchService.productSearch(request);
    }
}

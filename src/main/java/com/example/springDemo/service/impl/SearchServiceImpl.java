package com.example.springDemo.service.impl;

import com.example.springDemo.dto.ProductResponseDTO;
import com.example.springDemo.dto.SearchRequestDTO;
import com.example.springDemo.dto.SearchResponseDTO;
import com.example.springDemo.service.SearchService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {



    @Override
    public SearchResponseDTO productSearch(SearchRequestDTO request) {
        List<ProductResponseDTO> products = new ArrayList<>();
        SearchResponseDTO p = new SearchResponseDTO();
        products.add(new ProductResponseDTO(true, 1234, "samsung galaxy s4 blah blah blah", "Samsung Galaxy S4 128b"));
        products.add(new ProductResponseDTO(true, 134, "samsung galaxy s5 blah blah blah", "Samsung Galaxy S5 64b"));
        p.setProducts(products);
        return p;
    }
}

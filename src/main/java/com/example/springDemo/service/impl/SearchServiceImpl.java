package com.example.springDemo.service.impl;

import com.example.springDemo.client.SearchClient;
import com.example.springDemo.dto.ProductResponseDTO;
import com.example.springDemo.dto.SearchRequestDTO;
import com.example.springDemo.dto.SearchResponseDTO;
import com.example.springDemo.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchClient searchClient;


    @Override
    public SearchResponseDTO productSearch(SearchRequestDTO request) {

        Map<String, Object> products = searchClient.getProducts(request.getSearchTerm());
        SearchResponseDTO p = new SearchResponseDTO();
        List<ProductResponseDTO> productList = new ArrayList<>();

        ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)((HashMap<String, Object>)products.get("response")).get("docs");
        System.out.println(list.size());
        for(HashMap<String, Object> i : list){
            productList.add(new ProductResponseDTO((int) i.get("isInStock"), Double.parseDouble(i.get("offerPrice").toString()), i.get("name").toString(), i.get("description").toString()));
        }
        System.out.println(productList);
        p.setProducts(productList);
        return p;

    }
}

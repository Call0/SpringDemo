package com.example.springDemo.service.impl;

import com.example.springDemo.client.SearchClient;
import com.example.springDemo.constants.SolarFieldNames;
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
        SearchResponseDTO response = new SearchResponseDTO();
        List<ProductResponseDTO> productList = new ArrayList<>();

        ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)((HashMap<String, Object>)products.get("response")).get("docs");
        System.out.println(list.size());
        boolean stock;
        double price;
        for(HashMap<String, Object> obj : list){
            if((int) obj.get(SolarFieldNames.IN_STOCK) > 0){
                stock = true;
            } else{
                stock = false;
            }
            price = Double.parseDouble(obj.get(SolarFieldNames.OFFER_PRICE).toString());
            productList.add(new ProductResponseDTO(stock, (int) price, obj.get(SolarFieldNames.DESCRIPTION).toString(), obj.get(SolarFieldNames.NAME).toString()));
        }
        System.out.println(productList);
        response.setProducts(productList);
        return response;

    }
}

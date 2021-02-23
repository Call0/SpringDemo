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

        String locationSearch = "stockLocation:" + request.getLocation();
        Map<String, Object> productsByLocation = searchClient.getProducts(locationSearch);

        ArrayList<ProductResponseDTO> productList = (ArrayList<ProductResponseDTO>) getSearchtermBaseproducts(request.getSearchTerm());
        ArrayList<ProductResponseDTO> productListByLoaction = (ArrayList<ProductResponseDTO>) getSearchtermBaseproducts(locationSearch);

        SearchResponseDTO response = new SearchResponseDTO();
        response.setProducts(productList);
        response.setProductsByLocation(productListByLoaction);
        return response;

    }

    public List<ProductResponseDTO> getSearchtermBaseproducts(String query){

        boolean stock;
        double price;

        Map<String, Object> products = searchClient.getProducts(query);
        ArrayList<HashMap<String, Object>> locationList = (ArrayList<HashMap<String, Object>>)((HashMap<String, Object>)products.get("response")).get("docs");

        List<ProductResponseDTO> productsList = new ArrayList<>();

        for(HashMap<String, Object> obj : locationList){
            if ((int) obj.get(SolarFieldNames.IN_STOCK) > 0) {
                stock = true;
            } else {
                stock = false;
            }
            price = Double.parseDouble(obj.get(SolarFieldNames.OFFER_PRICE).toString());
            productsList.add(new ProductResponseDTO(stock, (int) price, obj.get(SolarFieldNames.DESCRIPTION).toString(), obj.get(SolarFieldNames.NAME).toString()));

        }
        return productsList;
    }
}

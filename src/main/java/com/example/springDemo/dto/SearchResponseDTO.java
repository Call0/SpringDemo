package com.example.springDemo.dto;

import java.util.HashMap;
import java.util.List;

public class SearchResponseDTO {
    private List<ProductResponseDTO> products;
    private HashMap<String, Object> productsMap;

    public List<ProductResponseDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponseDTO> products) {
        this.products = products;
    }

    public HashMap<String, Object> getProductsMap() {
        return productsMap;
    }

    public void setProductsMap(HashMap<String, Object> productsMap) {
        this.productsMap = productsMap;
    }
}

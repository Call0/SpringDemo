package com.example.springDemo.dto;

import java.util.HashMap;
import java.util.List;

public class SearchResponseDTO {
    private List<ProductResponseDTO> products;
    private HashMap<String, Object> productsMap;
    private HashMap<String, Object> productsByLocationMap;
    private List<ProductResponseDTO> productsByLocation;

    public HashMap<String, Object> getProductsByLocationMap() {
        return productsByLocationMap;
    }

    public void setProductsByLocationMap(HashMap<String, Object> productsByLocationMap) {
        this.productsByLocationMap = productsByLocationMap;
    }

    public List<ProductResponseDTO> getProductsByLocation() {
        return productsByLocation;
    }

    public void setProductsByLocation(List<ProductResponseDTO> productsByLocation) {
        this.productsByLocation = productsByLocation;
    }

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

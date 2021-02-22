package com.example.springDemo.dto;

public class ProductResponseDTO {
    private boolean inStock;
    private int salePrice;
    private String description;
    private String title;

    public ProductResponseDTO(boolean inStock, int salePrice, String description, String title) {
        this.inStock = inStock;
        this.salePrice = salePrice;
        this.description = description;
        this.title = title;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

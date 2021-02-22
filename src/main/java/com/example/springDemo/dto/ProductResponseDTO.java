package com.example.springDemo.dto;

public class ProductResponseDTO {
    private int inStock;
    private double salePrice;
    private String description;
    private String title;

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
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

    public ProductResponseDTO(int inStock, double salePrice, String description, String title) {
        this.inStock = inStock;
        this.salePrice = salePrice;
        this.description = description;
        this.title = title;
    }
}

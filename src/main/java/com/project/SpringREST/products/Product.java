package com.project.SpringREST.products;

public class Product {
    private int id;
    private String name;
    private float price;
    private String category;
    private String createdDate;
    private String updatedDate;

    public Product(int id, String name, float price, String category, String createdDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.createdDate = createdDate;
        this.updatedDate = createdDate;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setUpdatedDate(String date) {
        updatedDate = date;
    }
}

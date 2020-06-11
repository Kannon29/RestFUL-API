package com.project.SpringREST.products;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductData {
    private List<Product> products = new ArrayList<>();

    private static ProductData instance = null;

    public static ProductData getInstance() {
        if (instance == null) {
            instance = new ProductData();
        }
        return instance;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Product> getByCategory(String category) {
        List<Product> matchedCategory = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equals(category)) {
                matchedCategory.add(product);
            }
        }
        return matchedCategory;
    }

    public Product getByName(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    public Product getById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public Product createProduct(String name, float price, String category) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String createdDate = dateFormat.format(date);
        int idx = products.size();
        Product newProduct = new Product(idx, name, price, category, createdDate);

        for (Product product : products) {
            if (product.getName().equals(name)) {
                return null;
            }
        }
        products.add(newProduct);
        return newProduct;
    }

    public Product updateProductName(int id, String name) {
        if (products.get(id) != null) {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

            products.get(id).setName(name);
            products.get(id).setUpdatedDate(dateFormat.format(date));
        }
        return products.get(id);
    }

    public Product updateProductPrice(int id, float price) {
        if (products.get(id) != null) {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

            products.get(id).setPrice(price);
            products.get(id).setUpdatedDate(dateFormat.format(date));
        }
        return products.get(id);
    }

    public Product updateProductCategory(int id, String category) {
        if (products.get(id) != null) {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

            products.get(id).setCategory(category);
            products.get(id).setUpdatedDate(dateFormat.format(date));
        }
        return products.get(id);
    }

    public boolean deleteProduct(int id) {
        if (products.get(id) != null) {
            products.remove(id);
            return true;
        }
        return false;
    }
}

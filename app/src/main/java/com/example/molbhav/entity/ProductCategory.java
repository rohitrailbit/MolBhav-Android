package com.example.molbhav.entity;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

public class ProductCategory implements Serializable {
    private String id;
    private String name;
    private List<ProductCategory> subcategories;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductCategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<ProductCategory> subcategories) {
        this.subcategories = subcategories;
    }

    @NonNull
    @Override
    public String toString() {
        return "ProductCategory{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", subcategories=" + subcategories +
                '}';
    }
}

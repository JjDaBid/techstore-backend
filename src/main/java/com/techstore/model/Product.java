package com.techstore.model;

import jakarta.persistence.*;


@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "productName")
    private String productName;
    @Column(name = "brand")
    private String brand;
    @Column(name = "category")
    private String category;
    @Column(name = "price")
    private String price;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String imageFileName;
    @Column(name = "inStock")
    private Boolean inStock;


    public Product(Long id, String productName, String brand, String category, String price, String description, String imageFileName, Boolean inStock) {
        this.id = id;
        this.productName = productName;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.description = description;
        this.imageFileName = imageFileName;
        this.inStock = inStock;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }
}

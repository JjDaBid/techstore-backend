package com.techstore.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brandName")
    private String brandName;

    @Column(name = "country")
    private String country;

    public Brand(Long id, String brandName, String country) {
        this.id = id;
        this.brandName = brandName;
        this.country = country;
    }
    public Brand() {
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}



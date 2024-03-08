package com.techstore.services;

import com.techstore.exception.ResourceNotFoundException;
import com.techstore.model.Brand;
import com.techstore.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService{

    @Autowired
    BrandRepository brandRepository;

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getBrandById(Long id) {
        return brandRepository.findById(id).get();
    }

    @Override
    public Brand createBrand(Brand product) {
        return brandRepository.save(product);
    }

    @Override
    public Brand updateBrand(Long id, Brand brand) {
        Brand foundBrand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));

        foundBrand.setBrandName(brand.getBrandName());
        foundBrand.setCountry(brand.getCountry());

        Brand brandUpdated = brandRepository.save(foundBrand);

        return brandUpdated;
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteBrand(Long id) {
        Brand foundBrand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        brandRepository.delete(foundBrand);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}

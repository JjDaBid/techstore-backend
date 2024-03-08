package com.techstore.services;

import com.techstore.model.Brand;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface BrandService {
    List<Brand> getAllBrands();
    Brand getBrandById(Long id);
    Brand createBrand(Brand product);
    Brand updateBrand(Long id, Brand product);
    ResponseEntity<Map<String, Boolean>> deleteBrand(Long id);
}

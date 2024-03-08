package com.techstore.controller;

import com.techstore.model.Brand;
import com.techstore.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173/admin")
@RestController
@RequestMapping("/api/v1")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/brands")
    public List<Brand> listBrands(){
        return brandService.getAllBrands();
    }

    @GetMapping("brands/{id}")
    public ResponseEntity<Brand> listBrandById(@PathVariable Long id){
        Brand foundBrand = brandService.getBrandById(id);
        if (foundBrand != null) {
            return ResponseEntity.ok(foundBrand);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/brands")
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
        Brand createdBrand = brandService.createBrand(brand);
        if (createdBrand != null) {
            return ResponseEntity.ok(createdBrand);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/brands/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable Long id, @RequestBody Brand brandRequest){
        Brand brand = brandService.getBrandById(id);

        brand.setBrandName(brandRequest.getBrandName());
        brand.setCountry(brandRequest.getCountry());

        Brand brandUpdated = brandService.updateBrand(id, brand);
        return ResponseEntity.ok(brandUpdated);
    }


    @DeleteMapping("/brands/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    
    
}

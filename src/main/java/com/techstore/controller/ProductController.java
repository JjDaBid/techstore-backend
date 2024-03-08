package com.techstore.controller;

import com.techstore.model.Product;
import com.techstore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173/users")
@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> listProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("products/{id}")
    public ResponseEntity<Product> listProductById(@PathVariable Long id){
        Product foundProduct = productService.getProductById(id);
        if (foundProduct != null) {
            return ResponseEntity.ok(foundProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        if (createdProduct != null) {
            return ResponseEntity.ok(createdProduct);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productRequest){
        Product product = productService.getProductById(id)                ;

        product.setProductName(productRequest.getProductName());
        product.setDescription(productRequest.getDescription());
        product.setBrand(productRequest.getBrand());
        product.setCategory(productRequest.getCategory());
        product.setPrice(productRequest.getPrice());
        product.setImageFileName(productRequest.getImageFileName());
        product.setInStock(productRequest.getInStock());

        Product productUpdated = productService.updateProduct(id, product);
        return ResponseEntity.ok(productUpdated);
    }


    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}

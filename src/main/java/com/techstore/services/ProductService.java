package com.techstore.services;

import com.techstore.model.Product;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    ResponseEntity<Map<String, Boolean>> deleteProduct(Long id);
}

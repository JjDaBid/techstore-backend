package com.techstore.services;

import com.techstore.exception.ResourceNotFoundException;
import com.techstore.model.Product;
import com.techstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product foundProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        foundProduct.setProductName(product.getProductName());
        foundProduct.setDescription(product.getDescription());
        foundProduct.setBrand(product.getBrand());
        foundProduct.setCategory(product.getCategory());
        foundProduct.setPrice(product.getPrice());
        foundProduct.setImageFileName(product.getImageFileName());

        Product productUpdated = productRepository.save(foundProduct);
        return productUpdated;
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}

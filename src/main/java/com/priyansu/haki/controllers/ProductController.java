package com.priyansu.haki.controllers;

import com.priyansu.haki.models.Product;
import com.priyansu.haki.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Priyansu Sahoo
 * 11-04-2024
 * @Project haki
 */

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createProduct = productService.createProduct(product);

        return ResponseEntity.ok(createProduct);
    }

    @GetMapping("/getProductById")
    public ResponseEntity<Optional<Product>> getProductById(@RequestParam Long id) {
        Optional<Product> product = productService.getProductById(id);

//        return product.isPresent() ? ResponseEntity.ok(product.get()) : ResponseEntity.notFound().build();
        return ResponseEntity.ok(product);
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<Optional<Product>> updateProduct(@RequestParam Long id, @RequestBody Product productDetails) {
        Optional<Product> updateProduct = productService.updateProduct(id, productDetails);

        return ResponseEntity.ok(updateProduct);
    }

    @DeleteMapping("/deleteProduct")
    public ResponseEntity<Boolean> deleteProduct(@RequestParam Long id){
        return productService.deleteProduct(id);
    }
}

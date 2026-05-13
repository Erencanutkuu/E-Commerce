package com.atlascommerce.product.controller;

import com.atlascommerce.product.dto.CategoryRequest;
import com.atlascommerce.product.dto.ProductRequest;
import com.atlascommerce.product.dto.ProductResponse;
import com.atlascommerce.product.entity.Category;
import com.atlascommerce.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createCategory(request));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAllProducts(Pageable pageable) {
        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductResponse> updateStock(@PathVariable Long id,
                                                       @RequestParam int quantity) {
        return ResponseEntity.ok(productService.updateStock(id, quantity));
    }
}

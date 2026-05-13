package com.atlascommerce.product.service;

import com.atlascommerce.product.dto.CategoryRequest;
import com.atlascommerce.product.dto.ProductRequest;
import com.atlascommerce.product.dto.ProductResponse;
import com.atlascommerce.product.entity.Category;
import com.atlascommerce.product.entity.Product;
import com.atlascommerce.product.repository.CategoryRepository;
import com.atlascommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public Category createCategory(CategoryRequest request) {
        if (categoryRepository.existsByName(request.getName())) {
            throw new RuntimeException("Bu kategori zaten mevcut");
        }
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return categoryRepository.save(category);
    }

    public ProductResponse createProduct(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı"));

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setCategory(category);

        Product saved = productRepository.save(product);
        return toResponse(saved);
    }

    @Cacheable(value = "products", key = "#id")
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));
        return toResponse(product);
    }

    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(this::toResponse);
    }

    @CacheEvict(value = "products", key = "#id")
    @Transactional
    public ProductResponse updateStock(Long id, int quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));
        product.setStockQuantity(quantity);
        return toResponse(productRepository.save(product));
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getCategory().getId(),
                product.getCategory().getName()
        );
    }
}

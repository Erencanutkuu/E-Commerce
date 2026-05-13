package com.atlascommerce.product.repository;

import com.atlascommerce.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);   //name gore arama yapar optional sayesinde null donebilir

    boolean existsByName(String name);
}

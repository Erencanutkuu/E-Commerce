package com.atlascommerce.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    @NotBlank
    private String name;
    private String description;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Integer stockQuantity;
    @NotNull
    private long categoryId;

}

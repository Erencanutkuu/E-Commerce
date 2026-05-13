package com.atlascommerce.product.entity;



import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stockQuantity;

    // her güncelleme bu sayıyı artırır — eş zamanlı güncellemeyi önler
    @Version
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY) // lazy ile belli yerlerde kullanılıyor
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}

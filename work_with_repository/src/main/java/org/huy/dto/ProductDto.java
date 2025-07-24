package org.huy.dto;

import lombok.Data;
import org.huy.entity.ProductByCategory;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
public class ProductDto {

    private UUID productId;

    private String name;

    private String category;

    private BigDecimal price;

    private Integer stock;

    private Instant createdAt;

    public ProductDto() {

    }

    public static ProductDto fromProductByCategory(ProductByCategory entity) {
        ProductDto dto = new ProductDto();
        dto.setCategory(entity.getKey().getCategory());
        dto.setProductId(entity.getKey().getProductId());
        dto.setPrice(entity.getKey().getPrice());
        dto.setName(entity.getName());
        dto.setStock(entity.getStock());

        return dto;
    }
}

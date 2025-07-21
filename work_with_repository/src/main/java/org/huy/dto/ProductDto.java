package org.huy.dto;

import lombok.Data;

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
}

package org.huy.entity;

import lombok.*;
import org.huy.dto.ProductCreateRequest;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Table("product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @PrimaryKey
    @Column("product_id")
    private UUID productId;

    @Column("category")
    private String category;

    private String name;

    private BigDecimal price;

    private Integer stock;

    /** clustering key. */
    @Column("created_at")
    private Instant createdAt;
}

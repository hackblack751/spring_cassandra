package org.huy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class Product {

    @PrimaryKey
    @Column("product_id")
    private UUID productId;

    @Column("category")
    private String category;

    private String name;

    private BigDecimal price;

    private Integer stock;

    @Column("created_at")
    private Instant createdAt;

}

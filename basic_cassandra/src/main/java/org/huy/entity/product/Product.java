package org.huy.entity.product;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Table("products")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Product {

    @PrimaryKey
    private ProductKey key;

    @Column("name")
    private String name;

    @Column("price")
    private BigDecimal price;

    @Column("tags")
    private Set<String> tags;

    @Column("attributes")
    private Map<String, String> attributes;

    @Column("images")
    private List<String> images;

    @Column("created_at")
    private Instant createdAt;

    @Column("updated_at")
    private Instant updatedAt;

    @Column("dimension")
    private Dimension dimension;

}

package org.huy.entity;

import lombok.*;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.math.BigDecimal;
import java.util.UUID;

@Table("product_by_category")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductByCategory {

    @PrimaryKey
    private ProductByCategoryKey key;

    private String name;

    private Integer stock;

    @PrimaryKeyClass
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ProductByCategoryKey{

        @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
        private String category;

        @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED)
        @Column("product_id")
        private UUID productId;

        @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED)
        private BigDecimal price;
    }

    public static ProductByCategory fromProduct(Product product) {
        return ProductByCategory.builder()
                .key(makeKey(product))
                .name(product.getName())
                .stock(product.getStock())
                .build();
    }

    public static ProductByCategoryKey makeKey(Product product) {
        return ProductByCategoryKey.builder()
                .category(product.getCategory())
                .productId(product.getProductId())
                .price(product.getPrice())
                .build();
    }

}

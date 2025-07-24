package org.huy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.math.BigDecimal;
import java.util.UUID;

@Table("product_by_category")
@NoArgsConstructor
@Getter
@Setter
public class ProductByCategory {

    @PrimaryKey
    private ProductByCategoryKey key;

    private String name;

    private int stock;

    @PrimaryKeyClass
    @Getter
    @Setter
    @NoArgsConstructor
    public static class ProductByCategoryKey{

        @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
        private String category;

        @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED)
        @Column("product_id")
        private UUID productId;

        @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
        private BigDecimal price;
    }


}

package org.huy.entity.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.util.UUID;

@PrimaryKeyClass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductKey {

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private String category;

    @PrimaryKeyColumn(name = "product_id", type = PrimaryKeyType.CLUSTERED)
    private UUID productId;
}

package org.huy.repository;

import org.huy.entity.ProductByCategory;
import org.huy.entity.ProductByCategory.ProductByCategoryKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductByCategoryRepository extends CassandraRepository<ProductByCategory, ProductByCategoryKey> {

    @Query("""
            SELECT *
            FROM product_by_category
            WHERE category = :category
            """)
    List<ProductByCategory> findProductsByCategory(String category);

    @Query("""
            SELECT *
            FROM product_by_category
            WHERE category = :category
            AND price >= :min AND price <= :max
            """)
    List<ProductByCategory> findProductsInRangePrice(BigDecimal min, BigDecimal max, String category);
}

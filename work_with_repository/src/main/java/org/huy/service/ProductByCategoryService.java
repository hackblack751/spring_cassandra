package org.huy.service;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;
import com.datastax.oss.driver.api.core.metadata.schema.ClusteringOrder;
import com.datastax.oss.driver.api.querybuilder.CqlSnippet;
import com.datastax.oss.driver.api.querybuilder.Literal;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.term.Term;
import lombok.RequiredArgsConstructor;
import org.huy.dto.ProductDto;
import org.huy.entity.ProductByCategory;
import org.huy.repository.ProductByCategoryRepository;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductByCategoryService {

    private final ProductByCategoryRepository productByCategoryRepository;

    private final CassandraOperations cassandraTemplate;

    private final CqlSession cqlSession;

    public List<ProductDto> getProductByCategory(String category) {
       List<ProductByCategory> products = this.productByCategoryRepository.findProductsByCategory(category);

       return products.stream().map(ProductDto::fromProductByCategory).toList();
    }

    public List<ProductDto> getProductsInRangePrice(BigDecimal min, BigDecimal max, String category) {
        List<ProductByCategory> products = this.productByCategoryRepository.findProductsInRangePrice(min, max, category);
        return products.stream().map(ProductDto::fromProductByCategory).toList();
    }

    /**
     * Ví dụ sử dụng {@link CassandraOperations} + {@link SimpleStatementBuilder}.
     */
    public List<ProductDto> getProductsInCategories(List<String> categories) {

        List<ProductByCategory> products = this.cassandraTemplate.select(this.createStatement(categories),
                ProductByCategory.class);

        return products.stream().map(ProductDto::fromProductByCategory).toList();
    }

    private <T> Statement<?> createStatement(List<String> categories) {
        SimpleStatementBuilder statementBuilder = new SimpleStatementBuilder(
                "SELECT * FROM product_by_category WHERE category IN ?")
                .addPositionalValues(categories);

        SimpleStatement statement = statementBuilder.build();
        return statement;
    }


    public void foo() {
        int stockMin = 10;

        SimpleStatement statement = QueryBuilder.selectFrom("product_by_category")
                .all()
                .whereColumn("stock")
                .isGreaterThan(QueryBuilder.bindMarker("stockMin"))
                .orderBy("category", ClusteringOrder.DESC)
                .allowFiltering()
                .build(stockMin);

        this.cassandraTemplate.select(statement, ProductByCategory.class);
    }


}

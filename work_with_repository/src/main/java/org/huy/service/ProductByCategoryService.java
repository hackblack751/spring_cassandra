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
import org.huy.dto.ProductModifyRequest;
import org.huy.entity.Product;
import org.huy.entity.ProductByCategory;
import org.huy.repository.ProductByCategoryRepository;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    /**
     * Sử dụng {@link SimpleStatementBuilder} để build query.
     */
    private <T> Statement<?> createStatement(List<String> categories) {
        SimpleStatementBuilder statementBuilder = new SimpleStatementBuilder(
                "SELECT * FROM product_by_category WHERE category IN ?")
                .addPositionalValues(categories);

        SimpleStatement statement = statementBuilder.build();
        return statement;
    }

    public void createFromProduct(Product product) {
        ProductByCategory productByCategory = ProductByCategory.fromProduct(product);
        this.productByCategoryRepository.save(productByCategory);
    }

    public boolean deleteByKey(ProductByCategory.ProductByCategoryKey key) {
        Optional<ProductByCategory> result = this.productByCategoryRepository.findById(key);

        if(result.isEmpty()) {
            return false;
        }

        ProductByCategory productByCategory = result.get();
        this.productByCategoryRepository.delete(productByCategory);
        return true;
    }

    /**
     * Ví dụ UPDATE column Primary Key của Cassandra.
     * Thật ra chỉ là DELETE + INSERT vì Cassandra ko cho phép update giá trị cua column PK.
     */
    public void updateProductByCategory(ProductByCategory.ProductByCategoryKey key, ProductModifyRequest request) {

        Optional<ProductByCategory> result = this.productByCategoryRepository.findById(key);

        if(result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not exist product");
        }
        ProductByCategory productByCategory = result.get();

        // delete the exist product
        this.productByCategoryRepository.delete(productByCategory);

        // create a new one
        productByCategory = ProductByCategory.builder()
                .key(ProductByCategory.ProductByCategoryKey.builder()
                        .productId(UUID.fromString(request.getProductId()))
                        .category(request.getCategory().getValue())
                        .price(request.getPrice()).build()
                )
                .name(request.getName())
                .stock(request.getStock())
                .build();
        this.productByCategoryRepository.save(productByCategory);
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

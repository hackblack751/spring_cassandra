package org.huy.repository;

import org.huy.entity.Product;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends CassandraRepository<Product, UUID> {

    List<Product> findByName(String name);

    @Query("""
            SELECT *
            FROM product
            WHERE name = :name
            ALLOW FILTERING
            """)
    List<Product> findByNameAllowFiltering(String name);
}

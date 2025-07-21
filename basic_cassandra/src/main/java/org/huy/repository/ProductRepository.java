package org.huy.repository;

import org.huy.entity.product.Product;
import org.huy.entity.product.ProductKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CassandraRepository<Product, ProductKey> {

}

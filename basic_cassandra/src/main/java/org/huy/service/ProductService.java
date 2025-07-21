package org.huy.service;

import lombok.RequiredArgsConstructor;
import org.huy.dto.product.ProductCreateRequest;
import org.huy.entity.product.Product;
import org.huy.entity.product.ProductKey;
import org.huy.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductCreateRequest request) {
        Instant now = Instant.now();

        Product product = Product.builder()
                .key(new ProductKey(request.getCategory(), UUID.randomUUID()))
                .price(request.getPrice())
                .name(request.getName())
                .tags(request.getTags())
                .images(request.getImages())
                .dimension(request.getDimension())
                .attributes(request.getAttributes())
                .createdAt(now)
                .build();

        this.productRepository.save(product);
    }
}

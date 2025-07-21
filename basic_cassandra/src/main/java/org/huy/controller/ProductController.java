package org.huy.controller;

import lombok.RequiredArgsConstructor;
import org.huy.dto.product.ProductCreateRequest;
import org.huy.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductCreateRequest request) {
        this.productService.createProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

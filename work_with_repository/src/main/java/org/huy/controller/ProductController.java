package org.huy.controller;

import lombok.RequiredArgsConstructor;
import org.huy.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/search")
    public ResponseEntity<?> getProducts(@RequestParam String name) {
        return ResponseEntity.ok(this.productService.getProductsByName(name));
    }
}

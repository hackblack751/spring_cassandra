package org.huy.controller;

import lombok.RequiredArgsConstructor;
import org.huy.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/search")
    public ResponseEntity<?> getProducts(@RequestParam String name) {
        return ResponseEntity.ok(this.productService.getProductsByName(name));
    }

    @GetMapping("/range")
    public ResponseEntity<?> getProductsInRangePrice(@RequestParam BigDecimal min,
                                                     @RequestParam BigDecimal max) {

        if(min == null || max == null || min.compareTo(BigDecimal.ZERO) <= 0
                || max.compareTo(BigDecimal.ZERO) <= 0 || min.compareTo(max) > 0) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(this.productService.getProductsInRangePrice(min, max));
    }

    @GetMapping("/category")
    public ResponseEntity<?> getProductsInCategories(@RequestParam List<String> categories) {
        return ResponseEntity.ok(this.productService.getProductsInCategories(categories));
    }
}

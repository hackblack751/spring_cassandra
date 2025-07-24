package org.huy.controller;

import lombok.RequiredArgsConstructor;
import org.huy.service.ProductByCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/productsByCategory")
@RequiredArgsConstructor
public class ProductByCategoryController {

    private final ProductByCategoryService productByCategoryService;

    @GetMapping("/{category}")
    public ResponseEntity<?> getProductsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(this.productByCategoryService.getProductByCategory(category));
    }

    @GetMapping("/range")
    public ResponseEntity<?> getProductsInRangePrice(@RequestParam BigDecimal min, @RequestParam BigDecimal max,
        @RequestParam String category) {
        return ResponseEntity.ok(this.productByCategoryService.getProductsInRangePrice(min, max, category));
    }

    @GetMapping
    public ResponseEntity<?> getProductsInCategories(@RequestParam List<String> categories) {
        return ResponseEntity.ok(this.productByCategoryService.getProductsInCategories(categories));
    }
}

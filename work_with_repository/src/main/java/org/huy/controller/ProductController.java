package org.huy.controller;

import lombok.RequiredArgsConstructor;
import org.huy.dto.ProductCreateRequest;
import org.huy.dto.ProductModifyRequest;
import org.huy.service.ProductService;
import org.huy.util.AppUtils;
import org.huy.validator.ProductRequestValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ProductRequestValidator validator;

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

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductCreateRequest request) {
        this.validator.validateProductRequest(request);

        this.productService.createProduct(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProductById(@PathVariable UUID productId) {
         boolean isDeleted = this.productService.deleteByProductId(productId);
         if(!isDeleted) {
             return ResponseEntity.notFound().build();
         }
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody ProductModifyRequest request) {
        this.validator.validateProductRequest(request);
        this.productService.updateProduct(request);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{productId}/stock")
    public ResponseEntity<?> updateStock(@PathVariable String productId, @RequestParam Integer stock) {
        if(!AppUtils.isValidUUID(productId)) return ResponseEntity.badRequest().body("invalid product id");
        if(!AppUtils.isValidStock(stock)) return ResponseEntity.badRequest().body("invalid stock");

        this.productService.updateStock(UUID.fromString(productId), stock);

        return ResponseEntity.ok().build();
    }

}

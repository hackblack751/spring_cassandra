package org.huy.service;

import lombok.RequiredArgsConstructor;
import org.huy.dto.ProductCreateRequest;
import org.huy.dto.ProductDto;
import org.huy.dto.ProductModifyRequest;
import org.huy.entity.Product;
import org.huy.entity.ProductByCategory;
import org.huy.repository.ProductRepository;
import org.modelmapper.ModelMapper;
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
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductByCategoryService productByCategoryService;

    private final ModelMapper modelMapper;

    public List<ProductDto> getProductsByName(String name) {
//        List<Product> products = this.productRepository.findByName(name);

        List<Product> products = this.productRepository.findByNameAllowFiltering(name);

        return products.stream().map(p -> this.modelMapper.map(p, ProductDto.class)).toList();
    }

    /**
     * Ví dụ về range condition.
     */
    public List<ProductDto> getProductsInRangePrice(BigDecimal min, BigDecimal max) {
        List<Product> products = this.productRepository.findByPriceRange(min, max);

        return products.stream().map(p -> this.modelMapper.map(p, ProductDto.class)).toList();
    }

    /**
     * Ví dụ su dụng IN operator.
     */
    public List<ProductDto> getProductsInCategories(List<String> categories) {
        List<Product> products = this.productRepository.findInCategories(categories);

        return products.stream().map(p -> this.modelMapper.map(p, ProductDto.class)).toList();
    }


    /**
     * Ví du thực hiện INSERT bằng Spring Data Cassandra.
     */
    public ProductDto createProduct(ProductCreateRequest request) {
        // Saving product.
        Product product = Product.builder()
                .productId(UUID.randomUUID())
                .name(request.getName())
                .price(request.getPrice())
                .stock(request.getStock())
                .category(request.getCategory().getValue())
                .createdAt(Instant.now())
                .build();
        product = this.productRepository.save(product);

        // Saving product by category.
        this.productByCategoryService.createFromProduct(product);

        return this.modelMapper.map(product, ProductDto.class);
    }

    /**
     * Ví dụ thực hiện DELETE bằng Spring Data Cassandra.
     */
    public boolean deleteByProductId(UUID productId) {
        Optional<Product> result = this.productRepository.findById(productId);
        if(result.isEmpty()) {
            return false;
        }

        Product product = result.get();
        this.productRepository.delete(product);
        this.productByCategoryService.deleteByKey(ProductByCategory.makeKey(product));

        return true;
    }

    //    TODO: Refactor lại code này.
    /**
     * Ví dụ thực hiện UPDATE bằng Spring Data Cassandra.
     * </br> </br>
     * Lưu ý: Nguy cơ tạo ra lỗi nhất quán du liệu là rất cao. Cần tìm cơ chế giống như SQL transaction
     */
    public void updateProduct(ProductModifyRequest request) {
       Optional<Product> result = this.productRepository.findById(UUID.fromString(request.getProductId()));
       if(result.isEmpty()) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product not exist");
       }

       Product product = result.get();
       ProductByCategory.ProductByCategoryKey key = ProductByCategory.makeKey(product);

       product.setCategory(request.getCategory().getValue());
       product.setName(request.getName());
       product.setStock(request.getStock());
       product.setPrice(request.getPrice());

       this.productRepository.save(product);
       this.productByCategoryService.updateProductByCategory(key, request);
    }

}

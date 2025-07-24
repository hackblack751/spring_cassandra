package org.huy.service;

import lombok.RequiredArgsConstructor;
import org.huy.dto.ProductDto;
import org.huy.entity.Product;
import org.huy.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

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
}

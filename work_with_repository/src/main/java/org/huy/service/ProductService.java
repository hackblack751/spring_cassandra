package org.huy.service;

import lombok.RequiredArgsConstructor;
import org.huy.dto.ProductDto;
import org.huy.entity.Product;
import org.huy.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    public List<ProductDto> getProductsByName(String name) {
//        List<Product> products = this.productRepository.findByNameAllowFiltering(name);

        List<Product> products = this.productRepository.findByName(name);


        return products.stream().map(p -> this.modelMapper.map(p, ProductDto.class)).toList();
    }
}

package edu.school21.shopapi.service;

import edu.school21.openapi.model.ProductDto;
import edu.school21.shopapi.mapper.ProductMapper;
import edu.school21.shopapi.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public void save(ProductDto product) {
        productRepository.save(productMapper.toEntity(product));
    }

    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(productMapper::toDto).collect(Collectors.toList());
    }
}

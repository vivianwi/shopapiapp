package edu.school21.shopapi.controller;

import edu.school21.openapi.api.ProductApi;
import edu.school21.openapi.model.ProductDto;
import edu.school21.shopapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ProductResource implements ProductApi {

    private final ProductService productService;

    @Override
    public void createProduct(ProductDto productDto) {
        productService.save(productDto);
    }

    @Override
    public List<ProductDto> getProducts() {
        return productService.findAll();
    }
}

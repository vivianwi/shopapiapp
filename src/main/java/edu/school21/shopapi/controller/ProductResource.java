package edu.school21.shopapi.controller;

import edu.school21.openapi.api.ProductApi;
import edu.school21.openapi.model.DecreaseProductStockRequest;
import edu.school21.openapi.model.ProductDto;
import edu.school21.shopapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class ProductResource implements ProductApi {

    private final ProductService productService;

    @Override
    public void createProduct(ProductDto productDto) {
        productService.save(productDto);
    }

    @Override
    public void decreaseProductStock(UUID productId, DecreaseProductStockRequest decreaseProductStockRequest) {

    }

    @Override
    public void deleteProduct(UUID productId) {

    }

    @Override
    public ProductDto getProductById(UUID productId) {
        return null;
    }

}

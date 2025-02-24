package edu.school21.shopapi.controller;

import edu.school21.openapi.api.ProductApi;
import edu.school21.openapi.model.GetClientsPaginationParameter;
import edu.school21.openapi.model.ProductDto;
import edu.school21.shopapi.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private final ProductService productService;

    @Override
    @Transactional
    public void createProduct(ProductDto productDto) {
        productService.save(productDto);
    }

    @Override
    @Transactional
    public void decreaseProductStock(UUID productId, Integer decreaseAmount) {
        productService.decreaseProductStock(productId, decreaseAmount);
    }

    @Override
    public void deleteProduct(UUID productId) {
        productService.deleteProduct(productId);
    }

    @Override
    public ProductDto getProductById(UUID productId) {
        return productService.findByIdDto(productId);
    }

    @Override
    public List<ProductDto> getProducts(GetClientsPaginationParameter pagination) {
        return productService.getProducts(pagination.getLimit(), pagination.getOffset());
    }
}

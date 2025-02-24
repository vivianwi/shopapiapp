package edu.school21.shopapi.service;

import edu.school21.openapi.model.ProductDto;
import edu.school21.shopapi.error.ServiceException;
import edu.school21.shopapi.mapper.ProductMapper;
import edu.school21.shopapi.model.Image;
import edu.school21.shopapi.model.Product;
import edu.school21.shopapi.model.Supplier;
import edu.school21.shopapi.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final SupplierService supplierService;
    private final ProductMapper productMapper;
    private final ImageService imageService;

    public void save(ProductDto productDto) {
        Optional<Supplier> supplier = supplierService.findById(productDto.getSupplierId());
        if (supplier.isEmpty()) {
            throw new ServiceException("Supplier not found");
        }
        Product product = productRepository.findById(productDto.getId()).orElse(null);
        if (product == null) {
            product = productMapper.toEntity(productDto, supplier.get());
        } else {
            productMapper.updateEntityFromDto(productDto, product);
            product.setSupplier(supplier.get());
        }
        if (productDto.getImageId() != null) {
            imageService.findById(productDto.getImageId())
                    .ifPresent(product::setImage);
        }
        productRepository.save(product);
    }

    public Product findById(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ServiceException("Product not found"));
    }

    public ProductDto findByIdDto(UUID productId) {
        return productMapper.toDto(findById(productId));
    }

    public void decreaseProductStock(UUID productId, Integer decreaseAmount) {
        Product product = findById(productId);
        product.setAvailableStock(product.getAvailableStock() - decreaseAmount);
        if (product.getAvailableStock() <= 0) {
            throw new ServiceException("Cannot decrease product stock");
        }
        save(product);
    }

    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    public Optional<Image> findImageByProductId(UUID productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new ServiceException("Product not found");
        }
        return imageService.findById(product.get().getImage().getId());
    }

    public List<ProductDto> getProducts(Integer limit, Integer offset) {
        Pageable pageable;

        if (limit != null && offset != null) {
            int page = offset / limit;
            pageable = PageRequest.of(page, limit);
        } else {
            pageable = Pageable.unpaged();
        }
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.getContent()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteProduct(UUID productId) {
        if (!productRepository.existsById(productId)) {
            throw new ServiceException("Product not found");
        }
        productRepository.deleteById(productId);
    }
}

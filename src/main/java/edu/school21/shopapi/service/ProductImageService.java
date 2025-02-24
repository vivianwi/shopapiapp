package edu.school21.shopapi.service;

import edu.school21.shopapi.error.ServiceException;
import edu.school21.shopapi.model.Image;
import edu.school21.shopapi.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ProductImageService {

    private final ProductService productService;
    private final ImageService imageService;

    public void associateImageToProduct(UUID productId, UUID imageId) {
        Product product = productService.findById(productId);
        Image image = imageService.findById(imageId).orElseThrow(() -> new ServiceException("Image not found"));
        product.setImage(image);
        productService.save(product);
    }

    public void addImage(UUID productId, Resource body) {
        try {
            byte[] imageBytes = body.getInputStream().readAllBytes();
            Image image = imageService.saveImage(imageBytes);
            associateImageToProduct(productId, image.getId());
        } catch (IOException e) {
            throw new ServiceException("Failed to read image");
        }
    }

    public Resource findByProductId(UUID productId) {
        Product product = productService.findById(productId);
        if (product == null) {
            throw new ServiceException("Product not found");
        }
        return getImageByProductId(productId);
    }

    public Resource getImageByProductId(UUID productId) {
        Optional<Image> imageOptional = productService.findImageByProductId(productId);
        if (imageOptional.isPresent()) {
            byte[] imageBytes = imageOptional.get().getImage();
            return new ByteArrayResource(imageBytes);
        } else {
            throw new ServiceException("Image not found");
        }
    }


}






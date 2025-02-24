package edu.school21.shopapi.controller;

import edu.school21.openapi.api.ImageApi;
import edu.school21.openapi.model.GetClientsPaginationParameter;
import edu.school21.openapi.model.ImageDto;
import edu.school21.shopapi.service.ImageService;
import edu.school21.shopapi.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor

public class ImageController implements ImageApi {
    private final ImageService imageService;
    private final ProductImageService productImageService;

    @Override
    public void addImage(UUID productId, Resource body) {
        productImageService.addImage(productId, body);
    }

    @Override
    public void deleteImage(UUID imageId) {
        imageService.deleteImage(imageId);
    }

    @Override
    public Resource getImageById(UUID imageId) {
        return imageService.getImageById(imageId);
    }

    @Override
    public Resource getImageByProductId(UUID productId) {
        return productImageService.findByProductId(productId);
    }

    @Override
    public List<ImageDto> getImagesIds(GetClientsPaginationParameter pagination) {
        return imageService.getImages(pagination.getLimit(), pagination.getOffset());
    }

    @Override
    public void updateImage(UUID imageId, Resource body) {
        imageService.updateImage(imageId, body);
    }
}

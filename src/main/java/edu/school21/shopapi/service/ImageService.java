package edu.school21.shopapi.service;

import edu.school21.openapi.model.ImageDto;
import edu.school21.shopapi.error.ServiceException;
import edu.school21.shopapi.mapper.ImageMapper;
import edu.school21.shopapi.model.Image;
import edu.school21.shopapi.repository.ImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    public Image saveImage(byte[] imageBytes) {
        Image image = new Image();
        image.setImage(imageBytes);
        return imageRepository.save(image);
    }

    public Optional<Image> findById(UUID imageId) {
        return imageRepository.findById(imageId);
    }

    public Resource getImageById(UUID imageId) {
        Optional<Image> imageOptional = imageRepository.findById(imageId);
        if (imageOptional.isPresent()) {
            byte[] imageBytes = imageOptional.get().getImage();
            return new ByteArrayResource(imageBytes);
        } else {
            throw new ServiceException("Image not found");
        }
    }

    public List<ImageDto> getImages(Integer limit, Integer offset) {
        Pageable pageable;

        if (limit != null && offset != null) {
            int page = offset / limit;
            pageable = PageRequest.of(page, limit);
        } else {
            pageable = Pageable.unpaged();
        }
        Page<Image> imagePage = imageRepository.findAll(pageable);
        return imagePage.getContent()
                .stream()
                .map(imageMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateImage(UUID imageId, Resource resourceBody) {
        try {
            byte[] body = resourceBody.getInputStream().readAllBytes();
            Image image = imageRepository.findById(imageId).orElseThrow(() -> new ServiceException("No image found"));
            image.setImage(body);
            imageRepository.save(image);
        } catch (IOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void deleteImage(UUID imageId) {
        if (!imageRepository.existsById(imageId)) {
            throw new ServiceException("No image found");
        }
        imageRepository.deleteById(imageId);
    }
}



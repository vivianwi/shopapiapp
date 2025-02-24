package edu.school21.shopapi.repository;

import edu.school21.shopapi.model.Client;
import edu.school21.shopapi.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID>, JpaSpecificationExecutor<Client> {
}
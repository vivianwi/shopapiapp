package edu.school21.shopapi.repository;

import edu.school21.shopapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findById(UUID id);
}

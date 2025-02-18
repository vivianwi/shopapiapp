package edu.school21.shopapi.repository;

import edu.school21.shopapi.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Optional<Address> findById(UUID id);
}

package edu.school21.shopapi.service;

import edu.school21.openapi.model.AddressDto;
import edu.school21.openapi.model.SupplierDto;
import edu.school21.shopapi.error.ServiceException;
import edu.school21.shopapi.mapper.SupplierMapper;
import edu.school21.shopapi.model.Address;
import edu.school21.shopapi.model.Supplier;
import edu.school21.shopapi.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;
    private final AddressService addressService;


    @Transactional
    public void save(SupplierDto supplierDto) {
        Address savedAddress = addressService.save(supplierDto.getAddress());
        Supplier supplier = supplierMapper.toEntity(supplierDto);
        supplier.setAddress(savedAddress);
        supplierRepository.save(supplier);
    }

    public void deleteSupplier(UUID supplierId){
        if(findById(supplierId).isEmpty()){
            throw new ServiceException("Supplier not found");
        }
        supplierRepository.deleteById(supplierId);
    }

    public Optional<Supplier> findById(UUID id) {
        return supplierRepository.findById(id);
    }

    public SupplierDto findByIdToDto(UUID id) {
        return supplierRepository.findById(id)
                .map(supplierMapper::toDto)
                .orElseThrow(() -> new ServiceException("Supplier not found"));
    }

    @Transactional
    public void updateSupplierAddress(UUID supplierId, AddressDto addressDto) {
        Supplier supplier  = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new ServiceException("No such client"));
        addressService.updateAddress(supplier.getAddress(), addressDto);
    }

    public List<SupplierDto> getProducts(Integer limit, Integer offset) {
        Pageable pageable;

        if (limit != null && offset != null) {
            int page = offset / limit;
            pageable = PageRequest.of(page, limit);
        } else {
            pageable = Pageable.unpaged();
        }
        Page<Supplier> supplierPage = supplierRepository.findAll(pageable);
        return supplierPage.getContent()
                .stream()
                .map(supplierMapper::toDto)
                .collect(Collectors.toList());
    }


}

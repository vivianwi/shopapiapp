package edu.school21.shopapi.controller;


import edu.school21.openapi.api.SupplierApi;
import edu.school21.openapi.model.AddressDto;
import edu.school21.openapi.model.GetClientsPaginationParameter;
import edu.school21.openapi.model.SupplierDto;
import edu.school21.shopapi.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SupplierController implements SupplierApi {

    private final SupplierService supplierService;

    @Override
    public void createSupplier(SupplierDto supplierDto) {
        supplierService.save(supplierDto);
    }

    @Override
    public void deleteSupplier(UUID supplierId) {
        supplierService.deleteSupplier(supplierId);
    }

    @Override
    public SupplierDto getSupplierById(UUID supplierId) {
        return supplierService.findByIdToDto(supplierId);
    }

    @Override
    public List<SupplierDto> searchSuppliers(GetClientsPaginationParameter pagination) {
        return supplierService.getProducts(pagination.getLimit(), pagination.getOffset());
    }


    @Override
    public void updateSupplierAddress(UUID supplierId, AddressDto addressDto) {
        supplierService.updateSupplierAddress(supplierId, addressDto);
    }
}

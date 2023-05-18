package com.Pharma.Serviceimpl;



import com.Pharma.Entity.Supplier;
import com.Pharma.PayLoad.SupplierDto;
import com.Pharma.Repository.SupplierRepository;

import com.Pharma.Service.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SupplierDto getSupplierById(Long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new IllegalArgumentException("Supplier not found with ID: " + supplierId));
        return mapSupplierToDto(supplier);
    }

    @Override
    public List<SupplierDto> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers.stream()
                .map(this::mapSupplierToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierDto createSupplier(SupplierDto supplierDto) {
        Supplier supplier = mapDtoToSupplier(supplierDto);
        Supplier savedSupplier = supplierRepository.save(supplier);
        return mapSupplierToDto(savedSupplier);
    }

    @Override
    public SupplierDto updateSupplier(Long supplierId, SupplierDto supplierDto) {
        Supplier existingSupplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new IllegalArgumentException("Supplier not found with ID: " + supplierId));

        // Update the existing supplier entity with the data from the DTO
        existingSupplier.setSupplierName(supplierDto.getSupplierName());
        existingSupplier.setContactPerson(supplierDto.getContactPerson());
        existingSupplier.setContactNumber(supplierDto.getContactNumber());
        existingSupplier.setEmailAddress(supplierDto.getEmailAddress());

        Supplier updatedSupplier = supplierRepository.save(existingSupplier);
        return mapSupplierToDto(updatedSupplier);
    }

    @Override
    public void deleteSupplier(Long supplierId) {
        supplierRepository.deleteById(supplierId);
    }

    private SupplierDto mapSupplierToDto(Supplier supplier) {
        return modelMapper.map(supplier, SupplierDto.class);
    }

    private Supplier mapDtoToSupplier(SupplierDto supplierDto) {
        return modelMapper.map(supplierDto, Supplier.class);
    }
}

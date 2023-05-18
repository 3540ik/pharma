package com.Pharma.Service;



import com.Pharma.PayLoad.SupplierDto;

import java.util.List;

public interface SupplierService {
    SupplierDto getSupplierById(Long supplierId);

    List<SupplierDto> getAllSuppliers();

    SupplierDto createSupplier(SupplierDto supplierDto);

    SupplierDto updateSupplier(Long supplierId, SupplierDto supplierDto);

    void deleteSupplier(Long supplierId);
}

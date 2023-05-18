package com.Pharma.Controller;


import com.Pharma.PayLoad.SupplierDto;

import com.Pharma.Service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<SupplierDto> getSupplierById(@PathVariable Long supplierId) {
        SupplierDto supplierDto = supplierService.getSupplierById(supplierId);
        return ResponseEntity.ok(supplierDto);
    }

    @GetMapping
    public ResponseEntity<List<SupplierDto>> getAllSuppliers() {
        List<SupplierDto> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SupplierDto> createSupplier(@RequestBody SupplierDto supplierDto) {
        SupplierDto createdSupplier = supplierService.createSupplier(supplierDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSupplier);
    }

    @PutMapping("/{supplierId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SupplierDto> updateSupplier(
            @PathVariable Long supplierId, @RequestBody SupplierDto supplierDto) {
        SupplierDto updatedSupplier = supplierService.updateSupplier(supplierId, supplierDto);
        return ResponseEntity.ok(updatedSupplier);
    }

    @DeleteMapping("/{supplierId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long supplierId) {
        supplierService.deleteSupplier(supplierId);
        return ResponseEntity.noContent().build();
    }
}

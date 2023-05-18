package com.Pharma.Controller;

import com.Pharma.PayLoad.MedicationDto;

import com.Pharma.Service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/medications")
public class MedicationController {

    private final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping("/{medicationId}")

    public ResponseEntity<MedicationDto> getMedicationById(@PathVariable Long medicationId) {
        MedicationDto medicationDto = medicationService.getMedicationById(medicationId);
        return ResponseEntity.ok(medicationDto);
    }

    @GetMapping

    public ResponseEntity<List<MedicationDto>> getAllMedications() {
        List<MedicationDto> medicationDtos = medicationService.getAllMedications();
        return ResponseEntity.ok(medicationDtos);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MedicationDto> createMedication(@Valid @RequestBody MedicationDto medicationDto) {
        MedicationDto createdMedication = medicationService.createMedication(medicationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMedication);
    }

    @PutMapping("/{medicationId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MedicationDto> updateMedication(
            @PathVariable Long medicationId,
            @RequestBody MedicationDto medicationDto
    ) {
        MedicationDto updatedMedication = medicationService.updateMedication(medicationId, medicationDto);
        return ResponseEntity.ok(updatedMedication);
    }

    @DeleteMapping("/{medicationId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMedication(@PathVariable Long medicationId) {
        medicationService.deleteMedication(medicationId);
        return ResponseEntity.noContent().build();
    }
}

package com.Pharma.Service;


import com.Pharma.PayLoad.MedicationDto;

import java.util.List;

public interface MedicationService {
    MedicationDto getMedicationById(Long medicationId);

    List<MedicationDto> getAllMedications();

    MedicationDto createMedication(MedicationDto medicationDto);

    MedicationDto updateMedication(Long medicationId, MedicationDto medicationDto);

    void deleteMedication(Long medicationId);
}


package com.Pharma.Serviceimpl;

import com.Pharma.Entity.Medication;
import com.Pharma.PayLoad.MedicationDto;

import com.Pharma.Repository.MedicationRepository;
import com.Pharma.Service.MedicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepository medicationRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MedicationServiceImpl(MedicationRepository medicationRepository, ModelMapper modelMapper) {
        this.medicationRepository = medicationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MedicationDto getMedicationById(Long medicationId) {
        Medication medication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new IllegalArgumentException("Medication not found with ID: " + medicationId));
        return modelMapper.map(medication, MedicationDto.class);
    }

    @Override
    public List<MedicationDto> getAllMedications() {
        List<Medication> medications = medicationRepository.findAll();
        return medications.stream()
                .map(medication -> modelMapper.map(medication, MedicationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public MedicationDto createMedication(MedicationDto medicationDto) {
        Medication medication = modelMapper.map(medicationDto, Medication.class);
        Medication savedMedication = medicationRepository.save(medication);
        return modelMapper.map(savedMedication, MedicationDto.class);
    }

    @Override
    public MedicationDto updateMedication(Long medicationId, MedicationDto medicationDto) {
        Medication existingMedication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new IllegalArgumentException("Medication not found with ID: " + medicationId));

        // Update the existing medication entity with the data from the DTO
        existingMedication.setMedicationName(medicationDto.getMedicationName());
        existingMedication.setDescription(medicationDto.getDescription());
        existingMedication.setDosageForm(medicationDto.getDosageForm());
        existingMedication.setManufacturer(medicationDto.getManufacturer());
        existingMedication.setUnitPrice(medicationDto.getUnitPrice());
        existingMedication.setExpirationDate(medicationDto.getExpirationDate());

        Medication updatedMedication = medicationRepository.save(existingMedication);
        return modelMapper.map(updatedMedication, MedicationDto.class);
    }

    @Override
    public void deleteMedication(Long medicationId) {
        medicationRepository.deleteById(medicationId);
    }
}

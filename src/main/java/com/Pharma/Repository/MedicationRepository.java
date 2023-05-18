package com.Pharma.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Pharma.Entity.Medication;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
    // Add custom repository methods if needed
}

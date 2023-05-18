package com.Pharma.PayLoad;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicationDto {
    private Long medicationId;
    @NotEmpty
    @Size(min = 2, message = "Medication Name should have atleast 2 characters")
    private String medicationName;
    @NotEmpty
    @Size(min = 10, message = "Medication Name should have atleast 10 characters")
    private String description;
    private String dosageForm;
    private String manufacturer;
    private double unitPrice;
    @NotNull(message = "Expiration date is required")
    private LocalDate expirationDate;

}

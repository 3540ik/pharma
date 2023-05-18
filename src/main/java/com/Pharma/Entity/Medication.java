package com.Pharma.Entity;

import javax.persistence.*;

import com.Pharma.PayLoad.StockDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicationId;

    private String medicationName;

    private String description;

    private String dosageForm;

    private String manufacturer;

    private double unitPrice;

    private LocalDate expirationDate;


}

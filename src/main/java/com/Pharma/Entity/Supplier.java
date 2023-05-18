package com.Pharma.Entity;

import javax.persistence.*;

import com.Pharma.PayLoad.StockDto;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;

    private String supplierName;

    private String contactPerson;

    private String contactNumber;

    private String emailAddress;

}

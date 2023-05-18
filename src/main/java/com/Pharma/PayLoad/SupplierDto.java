package com.Pharma.PayLoad;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierDto {
    private Long supplierId;
    @NotEmpty
    private String supplierName;
    @NotEmpty
    private String contactPerson;

    @Pattern(regexp = "\\d{10}", message = "Contact number must be a 10-digit number")
    private String contactNumber;
    private String emailAddress;
    private List<StockDto> stocks;
}


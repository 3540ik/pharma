package com.Pharma.PayLoad;

import lombok.*;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockDto {
    private Long stockId;
    private Long medicationId;
    private int currentStockQuantity;
    @Min(value = 0, message = "Reorder level must be at least 0")
    private int reorderLevel;
    private Long supplierId;
}


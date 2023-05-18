package com.Pharma.Controller;



import com.Pharma.PayLoad.StockDto;

import com.Pharma.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<StockDto> getStockById(@PathVariable Long stockId) {
        StockDto stockDto = stockService.getStockById(stockId);
        return ResponseEntity.ok(stockDto);
    }

    @GetMapping
    public ResponseEntity<List<StockDto>> getAllStocks() {
        List<StockDto> stockDtoList = stockService.getAllStocks();
        return ResponseEntity.ok(stockDtoList);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StockDto> createStock(@RequestBody StockDto stockDto) {
        StockDto createdStockDto = stockService.createStock(stockDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStockDto);
    }

    @PutMapping("/{stockId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StockDto> updateStock(
            @PathVariable Long stockId, @RequestBody StockDto stockDto) {
        StockDto updatedStockDto = stockService.updateStock(stockId, stockDto);
        return ResponseEntity.ok(updatedStockDto);
    }

    @DeleteMapping("/{stockId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteStock(@PathVariable Long stockId) {
        stockService.deleteStock(stockId);
        return ResponseEntity.noContent().build();
    }
}

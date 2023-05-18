package com.Pharma.Serviceimpl;



import com.Pharma.Entity.Medication;
import com.Pharma.Entity.Stock;
import com.Pharma.Entity.Supplier;
import com.Pharma.PayLoad.StockDto;

import com.Pharma.Repository.MedicationRepository;
import com.Pharma.Repository.StockRepository;
import com.Pharma.Repository.SupplierRepository;
import com.Pharma.Service.StockService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final MedicationRepository medicationRepository;
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository, MedicationRepository medicationRepository,
                            SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.stockRepository = stockRepository;
        this.medicationRepository = medicationRepository;
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StockDto getStockById(Long stockId) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new IllegalArgumentException("Stock not found with ID: " + stockId));
        return mapStockToDto(stock);
    }

    @Override
    public List<StockDto> getAllStocks() {
        List<Stock> stocks = stockRepository.findAll();
        return stocks.stream()
                .map(this::mapStockToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StockDto createStock(StockDto stockDto) {
        Stock stock = mapDtoToStock(stockDto);
        Stock savedStock = stockRepository.save(stock);
        return mapStockToDto(savedStock);
    }

    @Override
    public StockDto updateStock(Long stockId, StockDto stockDto) {
        Stock existingStock = stockRepository.findById(stockId)
                .orElseThrow(() -> new IllegalArgumentException("Stock not found with ID: " + stockId));

        // Update the existing stock entity with the data from the DTO
        existingStock.setCurrentStockQuantity(stockDto.getCurrentStockQuantity());
        existingStock.setReorderLevel(stockDto.getReorderLevel());

        // Update the associated Medication
        Optional<Medication> medicationOptional = medicationRepository.findById(stockDto.getMedicationId());
        medicationOptional.ifPresent(existingStock::setMedication);

        // Update the associated Supplier
        Optional<Supplier> supplierOptional = supplierRepository.findById(stockDto.getSupplierId());
        supplierOptional.ifPresent(existingStock::setSupplier);

        Stock updatedStock = stockRepository.save(existingStock);
        return mapStockToDto(updatedStock);
    }

    @Override
    public void deleteStock(Long stockId) {
        stockRepository.deleteById(stockId);
    }

    private StockDto mapStockToDto(Stock stock) {
        return modelMapper.map(stock, StockDto.class);
    }

    private Stock mapDtoToStock(StockDto stockDto) {
        Stock stock = modelMapper.map(stockDto, Stock.class);

        // Set the associated Medication
        Optional<Medication> medicationOptional = medicationRepository.findById(stockDto.getMedicationId());
        medicationOptional.ifPresent(stock::setMedication);

        // Set the associated Supplier
        Optional<Supplier> supplierOptional = supplierRepository.findById(stockDto.getSupplierId());
        supplierOptional.ifPresent(stock::setSupplier);

        return stock;
    }
}

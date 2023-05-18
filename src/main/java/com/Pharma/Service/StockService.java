package com.Pharma.Service;



import com.Pharma.PayLoad.StockDto;

import java.util.List;

public interface StockService {
    StockDto getStockById(Long stockId);

    List<StockDto> getAllStocks();

    StockDto createStock(StockDto stockDto);

    StockDto updateStock(Long stockId, StockDto stockDto);

    void deleteStock(Long stockId);
}

package com.Pharma.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Pharma.Entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

}


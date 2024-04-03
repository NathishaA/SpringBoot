package com.java.rs.service;

import com.java.rs.model.Stock;
import com.java.rs.repository.StockRepo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepo stockRepo;

    public Stock createStock(@NonNull Stock stock) {
        return stockRepo.save(stock);
    }

    public List<Stock> getAllStocks() {
        return stockRepo.findAll();
    }

    public Stock getStockById(@NonNull Integer id) {
        return stockRepo.findById(id).orElse(null);
    }

    public boolean updateStock(int id, Stock stock) {
        if (getStockById(id) == null) {
            return false;
        }
        try {
            stockRepo.save(stock);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteStock(int id) {
        if (getStockById(id) == null) {
            return false;
        }
        stockRepo.deleteById(id);
        return true;
    }
}
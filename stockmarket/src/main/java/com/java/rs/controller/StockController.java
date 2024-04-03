package com.java.rs.controller;

import com.java.rs.model.Stock;
import com.java.rs.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/stock")
    public ResponseEntity<Stock> add(@RequestBody Stock stock) {
        Stock newStock = stockService.createStock(stock);
        if (newStock != null) {
            return new ResponseEntity<>(newStock, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/stocks")
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> stocks = stockService.getAllStocks();
        if (!stocks.isEmpty()) {
            return new ResponseEntity<>(stocks, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    @PutMapping("/stock/{stockId}")
    public ResponseEntity<Stock> updateStock(@PathVariable int stockId, @RequestBody Stock stock) {
        boolean updated = stockService.updateStock(stockId, stock);
        if (updated) {
            return new ResponseEntity<>(stock, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/stock/{stockId}")
    public ResponseEntity<Boolean> deleteStock(@PathVariable int stockId) {
        boolean deleted = stockService.deleteStock(stockId);
        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
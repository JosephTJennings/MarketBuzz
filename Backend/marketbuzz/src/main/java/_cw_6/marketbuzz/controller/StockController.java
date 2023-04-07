package _cw_6.marketbuzz.controller;

import _cw_6.marketbuzz.model.Stock;
import _cw_6.marketbuzz.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StockController {

    @Autowired
    StockRepository stockRepository;


    @GetMapping("stock")
    List<Stock> GetAllStock() {return stockRepository.findAll();}

    @PostMapping("stock/post/{ticker}/{currVal}")
    Stock PostStockByPath(@PathVariable String ticker, @PathVariable int currVal){
        Stock newStock = new Stock();
        newStock.setTicker(ticker);
        newStock.setCurrVal(currVal);
        stockRepository.save(newStock);
        return newStock;
    }

}

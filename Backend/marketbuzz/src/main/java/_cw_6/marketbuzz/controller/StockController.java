package _cw_6.marketbuzz.controller;

import _cw_6.marketbuzz.model.StaticStock;
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
    List<StaticStock> GetAllStock() {return stockRepository.findAll();}

    @PostMapping("stock/post/{ticker}/{currVal}")
    StaticStock PostStockByPath(@PathVariable String ticker, @PathVariable int currVal){
        StaticStock newStaticStock = new StaticStock();
        newStaticStock.setTicker(ticker);
        newStaticStock.setCurrVal(currVal);
        stockRepository.save(newStaticStock);
        return newStaticStock;
    }

}

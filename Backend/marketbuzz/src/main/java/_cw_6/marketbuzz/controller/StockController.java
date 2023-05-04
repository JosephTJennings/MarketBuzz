package _cw_6.marketbuzz.controller;

import _cw_6.marketbuzz.model.StaticStock;
import _cw_6.marketbuzz.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("stock/post")
    StaticStock PostStockByBody(@RequestBody StaticStock staticStock){
        stockRepository.save(staticStock);
        return staticStock;
    }

    @DeleteMapping("stock/delete/{ticker}")
    public Map<String, Boolean> DeleteStockByTicker(@PathVariable(value = "ticker") String ticker){
        List<StaticStock> currentStocks = stockRepository.findAll();
        for (StaticStock s : currentStocks){
            if (s.getTicker().equals(ticker)){
                stockRepository.delete(s);
                Map<String, Boolean> response = new HashMap<>();
                response.put("deleted", Boolean.TRUE);
                return response;
            }
        }
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.FALSE);
        return response;
    }

}

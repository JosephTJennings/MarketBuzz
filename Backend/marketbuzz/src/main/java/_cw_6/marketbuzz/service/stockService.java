package _cw_6.marketbuzz.service;


import _cw_6.marketbuzz.model.StockWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class stockService {
    public StockWrapper findStock(final String ticker){
        try{
            return new StockWrapper(YahooFinance.get(ticker));
        }
        catch(IOException e){
            System.out.println("Error");
        }
        return null;
    }

    public BigDecimal findPrice(final StockWrapper stock) throws IOException
    {
        return stock.getStock().getQuote(true).getPrice();
    }
}

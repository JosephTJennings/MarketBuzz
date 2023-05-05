package _cw_6.marketbuzz.service;


import _cw_6.marketbuzz.model.StockWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class stockService {
//
//    private final refreshService refreshService;
//
//    public StockWrapper findStock(final String ticker){
//        try{
//            return new StockWrapper(YahooFinance.get(ticker));
//        }
//        catch(IOException e){
//            System.out.println("Error");
//        }
//        return null;
//    }
//
////    public StockWrapper refresh (StockWrapper stock){
////        findStock()
////    }
//
//    public List<StockWrapper> findStocks(final List<String> tickers){
//        return tickers.stream().map(this::findStock).filter(Objects::nonNull).collect(Collectors.toList());
//    }
//
//    public BigDecimal findPrice(final StockWrapper stock) throws IOException
//    {
//        return stock.getStock().getQuote(refreshService.shouldRefresh(stock)).getPrice();
//    }
//
//    public BigDecimal updatePrice(StockWrapper stock) throws IOException{
//        return stock.getStock().getQuote(true).getPrice();
//    }
//    public List<BigDecimal> updatePrice(List<StockWrapper> stocks) throws IOException{
//        List<BigDecimal> newPrices = new ArrayList<BigDecimal>();
//        for(int i = 0; i < stocks.size(); i++) {
//            newPrices.add(updatePrice(stocks.get(i)));
//        }
//        return newPrices;
//    }
//
//    public BigDecimal findLastChangePercent(final StockWrapper stock) throws IOException
//    {
//        return stock.getStock().getQuote(refreshService.shouldRefresh(stock)).getChangeInPercent();
//    }
//

}

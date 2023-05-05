package _cw_6.marketbuzz.service;

import _cw_6.marketbuzz.model.StockWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class stockServiceTest {

    @Autowired
    private stockService stockService;

    @Test
    void invoke(){
        final StockWrapper stock = stockService.findStock("AMZN");
        System.out.println(stock.getStock());

        try{
            final BigDecimal price = stockService.findPrice(stock);
            System.out.println(price);

            final BigDecimal change = stockService.findLastChangePercent(stock);
            System.out.println(change);
        }
        catch(IOException e){
            System.out.println("Error");
        }
    }

    @Test
    void multiple() throws IOException, InterruptedException
    {
        final List<StockWrapper> stocks = stockService.findStocks(Arrays.asList("AMZN", "AAPL", "GME", "TSLA"));
        findPrices(stocks);
        System.out.println("trying to refresh AMZN");
        //stockService.findPrice(stocks.get(0));
        //Thread.sleep(11000);
        //findPrices(stocks);
        System.out.println("trying to refresh AMZN");
        System.out.println(stocks.get(0).getStock().getSymbol() + ":" + stockService.updatePrice(stocks.get(0)));

        System.out.println("trying to refresh all stocks");
        List<BigDecimal> newPrices = stockService.updatePrice(stocks);
        newPrices.forEach (n ->{
            System.out.println(n);
        });

        //stockService.findStock(stocks.get(0).getStock().getSymbol());
//        System.out.println(stocks.get(0).getStock().getSymbol() + ": " + stockService.findPrice(stocks.get(0)));
//        findPrices(stocks);
        //Thread.sleep(60000);

//        final StockWrapper jpm = stockService.findStock("JPM");
//        stocks.add(jpm);

        //findPrices(stocks);

    }

    private void findPrices(List<StockWrapper> stocks){
        stocks.forEach(stock -> {
            try{
                System.out.println(stock.getStock().getSymbol() + ": " + stockService.findPrice(stock));
            }
            catch (IOException e){
                System.out.println("Error");
            }
        });
    }

}
package _cw_6.marketbuzz.service;

import _cw_6.marketbuzz.model.StockWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;

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
        }
        catch(IOException e){
            System.out.println("Error");
        }


    }

}
package _cw_6.marketbuzz.service;

import _cw_6.marketbuzz.model.StockWrapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

@Service
public class refreshService {
//    private final Map<StockWrapper, Boolean> stocksToRefresh;
//
//    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//
//    private static final Duration refreshPeriod = Duration.ofSeconds(10);
//
//    public refreshService(){
//        stocksToRefresh = new HashMap<>();
//        setRefreshEvery10Seconds();
//    }
//
//
//    public boolean shouldRefresh(final StockWrapper stock){
//        if (!stocksToRefresh.containsKey(stock)){
//            stocksToRefresh.put(stock, false);
//            return true;
//        }
//        return stocksToRefresh.get(stock);
//    }
//
//    private void setRefreshEvery10Seconds() {
//        scheduler.scheduleAtFixedRate(() ->
//            stocksToRefresh.forEach((stock, value) -> {
//                if (stock.getLastAccessed().isBefore(LocalDateTime.now().minus(refreshPeriod))){
//                    System.out.println("Setting should refresh " + stock.getStock().getSymbol());
//                    stocksToRefresh.remove(stock);
//                    stocksToRefresh.put(stock.withLastAccessed(LocalDateTime.now()), true);
//                }
//            }), 0, 10, SECONDS);
//    }
}

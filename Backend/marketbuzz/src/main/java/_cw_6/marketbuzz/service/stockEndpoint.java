package _cw_6.marketbuzz.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import _cw_6.marketbuzz.model.StockWrapper;
import yahoofinance.Stock;
import org.springframework.beans.factory.annotation.Autowired;

@ServerEndpoint(value = "/stock/{ticker}") //stock/AMZN
public class stockEndpoint {
    private Session session;
    private static Set<stockEndpoint> stockEndpoints = new CopyOnWriteArraySet<>();
    private static HashMap<String, String> users = new HashMap<>();
    private StockWrapper currentStock;

    @Autowired
    private stockService stockService;

    @OnOpen
    public void onOpen(Session session, @PathParam("ticker") String ticker) throws IOException {
        System.out.println("made it into the socket");
        this.session = session;
        stockEndpoints.add(this);

        currentStock = stockService.findStock(ticker);
        Timer timer = new Timer();
        int begin = 0;
        int timeInterval = 1000;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                StockWrapper stock = stockService.findStock(ticker);
                System.out.println(stock.getStock().getSymbol());
            }
        }, begin, timeInterval);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        // WebSocket connection closes
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
        
    }
}
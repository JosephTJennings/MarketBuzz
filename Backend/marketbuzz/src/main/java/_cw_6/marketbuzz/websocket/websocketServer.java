package _cw_6.marketbuzz.websocket;

import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

@ServerEndpoint("/stocks/{ticker}")
@Component
public class websocketServer {

    
}

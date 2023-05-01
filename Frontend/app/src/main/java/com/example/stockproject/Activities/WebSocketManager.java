package com.example.stockproject.Activities;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;

import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketManager {
    private static WebSocketManager instance;
    private WebSocketClient webSocketClient;
    private String serverUrl;

    private WebSocketManager() {
    }

    public static synchronized WebSocketManager getInstance() {
        if (instance == null) {
            instance = new WebSocketManager();
        }
        return instance;
    }

    private WebSocketListener webSocketListener;

    public void setWebSocketListener(WebSocketListener listener) {
        this.webSocketListener = listener;
    }

    public void removeWebSocketListener() {
        this.webSocketListener = null;
    }

    public void connect(String serverUrl) {
        this.serverUrl = serverUrl;

        URI uri;
        try {
            uri = new URI(serverUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        webSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.d("WebSocket", "Connected");
            }

            @Override
            public void onMessage(String value) {
                Log.d("WebSocket", "Received value: " + value);
                if (webSocketListener != null) {
                    webSocketListener.onWebSocketMessage(value);
                }
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.d("WebSocket", "Closed");
            }

            @Override
            public void onError(Exception ex) {
                ex.printStackTrace();
            }
        };

        webSocketClient.connect();
    }

    public void sendMessage(String ticker) {
        if (webSocketClient != null && webSocketClient.isOpen()) {
            webSocketClient.send(ticker);
        }
    }

    public void disconnect() {
        if (webSocketClient != null && webSocketClient.isOpen()) {
            webSocketClient.close();
        }
    }

    public String getServerUrl() {
        return serverUrl;
    }
}

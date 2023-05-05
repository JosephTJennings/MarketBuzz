package com.example.stockproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockproject.Activities.adapter.recyclerView_interface;
import com.example.stockproject.Activities.adapter.stk_recyclerView_adapter;
import com.example.stockproject.Activities.model.StocksModel;
import com.example.stockproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import app.AppController;
import app.server.Const;
//import org.java_websocket.client.WebSocketClient;
//import org.java_websocket.drafts.Draft;
//import org.java_websocket.drafts.Draft_6455;
//import org.java_websocket.handshake.ServerHandshake;



/**
 * This activity is the Stocks Activity. This activity presents a list of stocks the user can choose from.
 */
public class StocksActivity extends AppCompatActivity implements recyclerView_interface, WebSocketListener {
    private Button HomeButton;
    private ImageButton RefreshButton;
    ArrayList<StocksModel> stocks = new ArrayList<>();
    private RecyclerView recyclerView;
    private RequestQueue volleyQueue;
    private String currentUser, currentType, currentMoney;

    /**
     * This method will create all the buttons, textViews, and Strings for the current Activity and set
     * each button to navigate to their corresponding activities.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stocks);

        HomeButton = (Button) findViewById(R.id.home_button3);
        RefreshButton = (ImageButton) findViewById(R.id.refresh_stocks);
        recyclerView = (RecyclerView) findViewById(R.id.stocksRecycler);
        volleyQueue = Volley.newRequestQueue(StocksActivity.this);
        currentUser = getIntent().getStringExtra("username");
        currentType = getIntent().getStringExtra("type");
        currentMoney = getIntent().getStringExtra("money");
        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("username", currentUser);
                intent.putExtra("type", currentType);
                intent.putExtra("money", currentMoney);
                //System.out.println("received and passing back: " + currentUser);
                startActivity(intent);
            }
        });
        setStocksModels();
        createWebSocketClient();
        RefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setStocksModels();
                //createWebSocketClient();
            }
        });

    }
    private void createWebSocketClient() {
        String serverUrl = "ws://10.0.2.2:8080/websocket/allStocks";
        WebSocketManager.getInstance().connect(serverUrl);
        WebSocketManager.getInstance().setWebSocketListener(StocksActivity.this);
    }
    /**
     * This method will produce a GET Request and produce a list of stocks. This method is called in onCreate.
     */
    public void setStocksModels() {
        stocks.clear();
        String url = Const.URL + "/stock";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject user = response.getJSONObject(i);
                                System.out.println("JSON object received");
                                String stockName = user.getString("ticker");
                                double value = user.getDouble("currVal");
                                int difference = user.getInt("diff");

                                StocksModel stockMod; //= new StocksModel(stockName, String.format("$%.2f", value));
                                if (difference > 0) stockMod = new StocksModel(stockName, String.format("$%.2f", value), R.drawable.baseline_arrow_upward_24);
                                else if (difference < 0) stockMod = new StocksModel(stockName, String.format("$%.2f", value), R.drawable.baseline_arrow_downward_24);
                                else stockMod = new StocksModel(stockName, String.format("$%.2f", value), R.drawable.baseline_neutral_24);
                                stocks.add(stockMod);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        refreshRecyclerView();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        volleyQueue.add(request);
    }

    /**
     * This method will refresh the recyclerView.
     */
    public void refreshRecyclerView() {
        stk_recyclerView_adapter adapter = new stk_recyclerView_adapter(StocksActivity.this, stocks, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(StocksActivity.this));
    }

    /**
     * This method will navigate the user to the designated ManageStockActivity for the selected stock in the list.
     * @param position The position of the stock on the list.
     */
    @Override
    public void onItemClick(int position) {
        Intent stock = new Intent(getApplicationContext(), ManageStockActivity.class);
        stock.putExtra("stockName", stocks.get(position).getStockName());
        stock.putExtra("value", stocks.get(position).getValue());
        stock.putExtra("username", currentUser);
        stock.putExtra("change", stocks.get(position).getChange());
        WebSocketManager.getInstance().disconnect();
        startActivity(stock);
    }

    @Override
    public void onWebSocketMessage(String message) {
        String[] stocks = message.split(",");
        for (int i = 0; i < stocks.length; i++) {
            String[] param = stocks[i].split(":");
            String stockName = param[0];
            double value = Double.parseDouble(param[1]);
            int difference = Integer.parseInt(param[2]);
            StocksModel stk = getStock(stockName);
            stk.setValue(String.valueOf(value));
            stk.setChange(difference);
        }
        refreshRecyclerView();
    }

    public StocksModel getStock(String ticker) {
        for (int i = 0; i < stocks.size(); i++) {
            if (stocks.get(i).getStockName().equals(ticker)) {
                return stocks.get(i);
            }
        }
        return null;
    }
}

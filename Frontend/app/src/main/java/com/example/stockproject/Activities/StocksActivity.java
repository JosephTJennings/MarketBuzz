package com.example.stockproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import app.server.Const;

public class StocksActivity extends AppCompatActivity implements recyclerView_interface {
    private Button HomeButton;
    private ImageButton RefreshButton;
    ArrayList<StocksModel> stocks = new ArrayList<>();
    private RecyclerView recyclerView;
    private RequestQueue volleyQueue;
    private String currentUser;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stocks);

        HomeButton = (Button) findViewById(R.id.home_button3);
        RefreshButton = (ImageButton) findViewById(R.id.refresh_stocks);
        recyclerView = (RecyclerView) findViewById(R.id.stocksRecycler);
        volleyQueue = Volley.newRequestQueue(StocksActivity.this);
        currentUser = getIntent().getStringExtra("username");
        HomeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("username", currentUser);
                //System.out.println("received and passing back: " + currentUser);
                startActivity(intent);
            }
        });
        setStocksModels();

        RefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setStocksModels();
            }
        });
    }

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
                                //TODO: Add the symbols to indicate level of change...
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
    public void refreshRecyclerView() {
        stk_recyclerView_adapter adapter = new stk_recyclerView_adapter(StocksActivity.this, stocks, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(StocksActivity.this));
    }

    @Override
    public void onItemClick(int position) {
        Intent stock = new Intent(getApplicationContext(), ManageStockActivity.class);
        stock.putExtra("stockName", stocks.get(position).getStockName());
        stock.putExtra("value", stocks.get(position).getValue());
        stock.putExtra("username", currentUser);
        stock.putExtra("change", stocks.get(position).getChange());
        startActivity(stock);
    }
}

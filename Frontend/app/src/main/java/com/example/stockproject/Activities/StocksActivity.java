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

public class StocksActivity extends AppCompatActivity {
    private Button HomeButton;
    private ImageButton RefreshButton;
    ArrayList<StocksModel> stocks = new ArrayList<>();
    private RecyclerView recyclerView;
    private RequestQueue volleyQueue;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stocks);

        HomeButton = (Button) findViewById(R.id.home_button3);
        RefreshButton = (ImageButton) findViewById(R.id.refresh_stocks);
        recyclerView = (RecyclerView) findViewById(R.id.stocksRecycler);
        volleyQueue = Volley.newRequestQueue(StocksActivity.this);
        HomeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //System.out.println("received and passing back: " + currentUser);
                startActivity(intent);
            }
        });
        setStocksModels();

        RefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshRecyclerView();
            }
        });
        refreshRecyclerView();
    }

    public void setStocksModels() {
        //volleyQueue = Volley.newRequestQueue(FollowersActivity.this);
        String url = "https://0589b6d4-7542-4459-abc5-12f5174f55ee.mock.pstmn.io/stocks"; //"http://coms-309-019.class.las.iastate.edu:8080/people";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject user = response.getJSONObject(i);
                                System.out.println("JSON object received");
                                int position = i;
                                String stockName = user.getString("Name");
                                String value = "$" + user.getString("Value");
                                int difference = user.getInt("Difference");
                                StocksModel stockMod;
                                if (difference > 0) stockMod = new StocksModel(stockName, value, R.drawable.baseline_arrow_upward_24);
                                else if (difference < 0) stockMod = new StocksModel(stockName, value, R.drawable.baseline_arrow_downward_24);
                                else stockMod = new StocksModel(stockName, value, R.drawable.baseline_neutral_24);
                                stocks.add(stockMod);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
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
        stk_recyclerView_adapter adapter = new stk_recyclerView_adapter(StocksActivity.this, stocks);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(StocksActivity.this));
    }
}

package com.example.stockproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.stockproject.Activities.adapter.holdings_recyclerView_adapter;
import com.example.stockproject.Activities.adapter.recyclerView_interface;
import com.example.stockproject.Activities.model.HoldingsModel;
import com.example.stockproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.server.Const;
import app.server.CustomRequest;
/**
 * This activity is the Profile Activity. This activity presents a list of stocks the user can choose from and allows the user to navigate to other activities.
 */
public class ProfileActivity extends AppCompatActivity implements recyclerView_interface {
    private Button HomeButton;
    private RequestQueue volleyQueue;
    private RecyclerView recyclerView;
    private ArrayList<HoldingsModel> currentHoldings = new ArrayList<>();
    private String currentUser, currentType, currentMoney, currentValuation;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView userName = findViewById(R.id.text_username);
        TextView valuation = findViewById(R.id.text_valuation);
        recyclerView = findViewById(R.id.holdingRecycler);
        currentUser = getIntent().getStringExtra("username");
        currentType = getIntent().getStringExtra("type");
        currentMoney = getIntent().getStringExtra("money");
        if (currentUser == null) {
            currentUser = "srhusted";
        }
        if (currentMoney == null) {
            currentMoney = "$1000.00";
        }
        if (currentType == null) {
            currentType = "Admin";
        }
        if (currentValuation == null) {
            currentValuation = "$1000.00";
        }
        userName.setText(currentUser);
        valuation.setText(currentValuation); //TODO: VALUATION FOR THE USER
        volleyQueue = Volley.newRequestQueue(ProfileActivity.this);

        Button options = findViewById(R.id.button_options);
        Button following = findViewById(R.id.button_following);
        HomeButton = (Button) findViewById(R.id.home_button01);
        HomeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("username", currentUser);
                intent.putExtra("type", currentType);
                intent.putExtra("money", currentMoney);
                intent.putExtra("valuation", currentValuation);
                //System.out.println("received and passing back: " + currentUser);
                startActivity(intent);
            }
        });
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OptionsActivity.class);
                intent.putExtra("username", currentUser);
                intent.putExtra("type", currentType);
                intent.putExtra("money", currentMoney);
                intent.putExtra("valuation", currentValuation);
                //System.out.println("received and passing back: " + currentUser);
                startActivity(intent);
            }
        });
        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FollowersActivity.class);
                intent.putExtra("username", currentUser);
                intent.putExtra("type", currentType);
                intent.putExtra("money", currentMoney);
                intent.putExtra("valuation", currentValuation);
                //System.out.println("received and passing back: " + currentUser);
                startActivity(intent);
            }
        });

        setCurrentHoldings();
    }
    /**
     * This method will produce a GET Request and produce a list of stocks. This method is called in onCreate.
     */
    public void setCurrentHoldings() {
        Map<String, String> map = new HashMap<>();
        map.put("username", currentUser);
        JSONObject obj = new JSONObject(map);
        CustomRequest request = new CustomRequest(Request.Method.POST, Const.URL + "/person/stocks", obj,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject holding = response.getJSONObject(i);
                                System.out.println("JSON object received");

                                int rank = i + 1;
                                String ticker = holding.getString("stockTicker");
                                double price = holding.getJSONObject("stock").getInt("currVal");
                                int quantity = holding.getInt("quantity");
                                double total = quantity * price;
                                TextView valuation = findViewById(R.id.text_valuation);
                                double currentCash = Double.valueOf(valuation.getText().toString().substring(1));
                                valuation.setText("$" + String.valueOf(currentCash - total));

                                HoldingsModel newHolding = new HoldingsModel(rank, ticker, (int) price, quantity, total);
                                currentHoldings.add(newHolding);
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
        holdings_recyclerView_adapter adapter = new holdings_recyclerView_adapter(ProfileActivity.this, currentHoldings, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ProfileActivity.this));
    }
    /**
     * This method will navigate the user to the designated ManageStockActivity for the selected stock in the list.
     * @param position The position of the stock on the list.
     */
    @Override
    public void onItemClick(int position) {
        Intent stock;
        stock = new Intent(getApplicationContext(), ManageStockActivity.class);
        stock.putExtra("stockName", currentHoldings.get(position).getTicker());
        stock.putExtra("username", currentUser);
        stock.putExtra("value", Double.toString(currentHoldings.get(position).getPrice()));
        startActivity(stock);
    }
}

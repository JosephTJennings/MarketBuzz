package com.example.stockproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.example.stockproject.R;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private Button HomeButton;
    private RequestQueue volleyQueue;
    private RecyclerView recyclerView;
    private ArrayList<HoldingsModel> currentHoldings = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView userName = findViewById(R.id.text_username);
        recyclerView = findViewById(R.id.holdingRecycler);
        //userName.setText(getIntent().getStringExtra("username"));
        userName.setText("TEMP");

        Button optionsButton = findViewById(R.id.button_options);
        Button followingButton = findViewById(R.id.button_following);
        HomeButton = (Button) findViewById(R.id.home_button01);
        HomeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //System.out.println("received and passing back: " + currentUser);
                startActivity(intent);
            }
        });
        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OptionsActivity.class);
                //System.out.println("received and passing back: " + currentUser);
                startActivity(intent);
            }
        });
        followingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FollowersActivity.class);
                //System.out.println("received and passing back: " + currentUser);
                startActivity(intent);
            }
        });

        setCurrentHoldings();
        //refreshRecyclerView();
        System.out.println("fml");
    }

    public void setCurrentHoldings() {
        for(int i = 0; i < 10; i++) {
            int rank = 1;
            String ticker = "MSFT";
            int price = 300;
            int quantity = 15;
            int total = quantity * price;

            HoldingsModel newHolding = new HoldingsModel(rank, ticker, price, quantity, total);
            currentHoldings.add(newHolding);
        }
        /**
        String url = "http://coms-309-019.class.las.iastate.edu:8080/people/stocks";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject holding = response.getJSONObject(i);
                                System.out.println("JSON object received");

                                int rank = 1;
                                String ticker = holding.getString("ticker");
                                int price = Integer.valueOf(holding.getString("price"));
                                int quantity = Integer.valueOf(holding.getString("quantity"));
                                int total = quantity * price;

                                HoldingsModel newHolding = new HoldingsModel(ticker, price, quantity);
                                currentHoldings.add(newHolding);
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
         **/
    }

    public void refreshRecyclerView() {
        holdings_recyclerView_adapter adapter = new holdings_recyclerView_adapter(ProfileActivity.this, currentHoldings);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ProfileActivity.this));
    }
}

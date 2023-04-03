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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class ViewedUserActivity extends AppCompatActivity implements recyclerView_interface {
    private Button HomeButton;
    private TextView Text1, viewUsername, viewValuation;
    private ArrayList<UserStocksModel> userStocks = new ArrayList<>();
    private RecyclerView recyclerView;
    private String currentUser;
    private RequestQueue volleyQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vieweduser);

        HomeButton = (Button) findViewById(R.id.home_button2);
        Text1 = (TextView) findViewById(R.id.Text1);
        viewUsername = (TextView) findViewById(R.id.viewedUsername);
        viewValuation = (TextView) findViewById(R.id.viewedUserValuation);
        currentUser = getIntent().getStringExtra("username");
        recyclerView = findViewById(R.id.recycle_userStocks);
        volleyQueue = Volley.newRequestQueue(ViewedUserActivity.this);

        viewUsername.setText(getIntent().getStringExtra("viewedUsername"));
        HomeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("username", currentUser);
                //System.out.println("received and passing back: " + currentUser);
                startActivity(intent);
            }
        });
        setUserStockModels();
    }
    public void setUserStockModels() {
        userStocks.clear();
        String url = "https://0589b6d4-7542-4459-abc5-12f5174f55ee.mock.pstmn.io/stocks?id=2";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        int i = 1;
                        Iterator<String> keys = response.keys();
                        while(keys.hasNext()) {
                            try {
                                System.out.println("JSON object received");
                                String pos = Integer.toString(i);
                                String ticker = keys.next();
                                //TODO: Get the price for the current price for this stock somehow...
                                String price = "0";
                                String quantity = "" + response.getInt(ticker);
                                UserStocksModel userStocksMod = new UserStocksModel(pos, ticker, price, quantity);
                                userStocks.add(userStocksMod);
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
        usrStk_recyclerView_adapter adapter = new usrStk_recyclerView_adapter(ViewedUserActivity.this, userStocks, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewedUserActivity.this));
    }

    @Override
    public void onItemClick(int position) {
        Intent stock = new Intent(getApplicationContext(), ManageStockActivity.class);
        stock.putExtra("stockName", userStocks.get(position).getTicker());
        stock.putExtra("username", currentUser);
        startActivity(stock);
    }
}

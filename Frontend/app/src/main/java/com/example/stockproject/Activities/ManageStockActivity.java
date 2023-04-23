package com.example.stockproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import app.server.Const;
import app.utils.BasicUtils;
/**
 * This activity is the ManageStock Activity. This activity presents a stock with several buttons to increase or decrease the number of the selected stock.
 */
public class ManageStockActivity extends AppCompatActivity {
    private Button homeButton, stocksButton, buyStocks, sellStocks;
    private TextView stockName, stockPrice, currentMoney, currentNumStock;
    private ImageView change;
    private String currentUser, currentStock, value;
    private int currentChange;
    private EditText numStks;
    private RequestQueue volleyQueue;
    /**
     * This method will create all the buttons, textViews, and Strings for the current Activity and set
     * each button to navigate to their corresponding activities.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managestock);
        volleyQueue = Volley.newRequestQueue(ManageStockActivity.this);
        currentUser = getIntent().getStringExtra("username");
        if (currentUser == null) {
            currentUser = "srhusted";
        }
        //TODO: get value and change by using POST Request for stock
        value = getIntent().getStringExtra("value");
        currentChange = getIntent().getIntExtra("change", R.drawable.baseline_neutral_24);


        currentStock = getIntent().getStringExtra("stockName");
        homeButton = (Button) findViewById(R.id.home_button7);
        stocksButton = (Button) findViewById(R.id.stocks_button);
        buyStocks = (Button) findViewById(R.id.buyStocks);
        sellStocks = (Button) findViewById(R.id.sellStocks);
        stockName = (TextView) findViewById(R.id.stkName);
        numStks = (EditText) findViewById(R.id.numStocks);
        stockPrice = (TextView) findViewById(R.id.price);
        currentNumStock = (TextView) findViewById(R.id.textView3);
        currentMoney = (TextView) findViewById(R.id.textView);
        change = (ImageView) findViewById(R.id.changeInPrice);

        stockName.setText(currentStock);
        stockPrice.setText(value);
        change.setImageResource(currentChange);
        getPersonInformation();
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("username", currentUser);
                startActivity(intent);
            }
        });

        stocksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StocksActivity.class);
                intent.putExtra("username", currentUser);
                startActivity(intent);
            }
        });

        buyStocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptBuyStocks();
            }
        });

        sellStocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSellStocks();
            }
        });
    }
    /**
     * This method will produce a POST Request and generate all the data for the page. This method is called in onCreate.
     */
    private void getPersonInformation() {
        Map<String, String> map = new HashMap<>();
        map.put("username", currentUser);
        JSONObject obj = new JSONObject(map);
//        JSONArray obj = new JSONArray();
//        obj.put(currentUser);
        JsonObjectRequest request = new JsonObjectRequest(com.android.volley.Request.Method.POST, Const.URL + "/person/data", obj, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String successMessage = "Received " + currentUser + "'s information!";
                Toast.makeText(ManageStockActivity.this, successMessage, Toast.LENGTH_SHORT + 1).show();
                try {
                    String nm = "You currently have $" + Integer.toString(response.getInt("cashValue")) + ".";
                    currentMoney.setText(nm);
                    String ns = "";
                    JSONArray stocks = (JSONArray) response.get("ownsList");
                    for (int i = 0; i < stocks.length(); i++) {
                        JSONObject stock = stocks.getJSONObject(i);
                        String ticker = stock.getString("stockTicker");
                        if (ticker.equals(currentStock)) {
                            ns = "You currently have " + stock.getInt("quantity") + " shares of this Company.";
                            break;
                        }
                    }
                    if (ns.equals("")) ns = "You currently have 0 shares of this Company.";
                    currentNumStock.setText(ns);
                }
                catch (Exception e) {
                    Toast.makeText(ManageStockActivity.this, "Cannot find either cashValue or stocks.", Toast.LENGTH_SHORT + 1).show();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ManageStockActivity.this, "Error, unable to add .", Toast.LENGTH_SHORT + 1).show();
            }
        }) {
            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        volleyQueue.add(request);
    }

    /**
     * This method is for buying stocks.
     */
    private void attemptBuyStocks() {
        ArrayList<String> person = new ArrayList<String>();
//        .add();
        Map<String, String> map = new HashMap<>();
        int quantity = Integer.parseInt(numStks.getText().toString());
        if (quantity > 0) {
            map.put("owner", currentUser);
            map.put("stock", currentStock);
            map.put("quantity", String.valueOf(quantity));
            JSONObject obj = new JSONObject(map);
            JsonObjectRequest request = new JsonObjectRequest(com.android.volley.Request.Method.POST, Const.URL + "/people/stocks/buy", obj, new com.android.volley.Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    String successMessage = null;
                    String ns = "";
                    try {
                        successMessage = "User " + currentUser + " has bought " + response.getInt("quantity") + " shares!";
                        ns = "You currently have " + response.getInt("quantity") + " shares of this Company.";
                        currentNumStock.setText(ns);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    Toast.makeText(ManageStockActivity.this, successMessage, Toast.LENGTH_SHORT + 1).show();
                }
            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ManageStockActivity.this, "Error, unable to add .", Toast.LENGTH_SHORT + 1).show();
                }
            }) {
                /**
                 * Passing some request headers
                 */
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json; charset=utf-8");
                    return headers;
                }
            };

            volleyQueue.add(request);
        }
        else {
            //TODO: Add an error message for value being less than 0...
        }
    }
    /**
     * This method is for selling stocks.
     */
    private void attemptSellStocks() {
        ArrayList<String> person = new ArrayList<String>();
//        .add();
        Map<String, String> map = new HashMap<>();
        int quantity = Integer.parseInt(numStks.getText().toString());
        if (quantity > 0) {
            map.put("owner", currentUser);
            map.put("stock", currentStock);
            map.put("quantity", String.valueOf(quantity));
            JSONObject obj = new JSONObject(map);
            System.out.println("sell attempt");
            JsonObjectRequest request = new JsonObjectRequest(com.android.volley.Request.Method.POST, Const.URL + "/people/stocks/sell", obj, new com.android.volley.Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    String successMessage = "";
                    String ns = "";
                    try {
                        successMessage = "User " + currentUser + " has sold " + response.getInt("quantity") + " shares!";
                        ns = "You currently have " + response.getInt("quantity") + " shares of this Company.";
                        currentNumStock.setText(ns);
                    }
                    catch (Exception e){
                        successMessage = "Yay, the message didn't return correctly! Whoopee!";
                    }
                    Toast.makeText(ManageStockActivity.this, successMessage, Toast.LENGTH_SHORT + 1).show();
                }
            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ManageStockActivity.this, "Error, unable to add .", Toast.LENGTH_SHORT + 1).show();
                }
            }) {
                /**
                 * Passing some request headers
                 */
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json; charset=utf-8");
                    return headers;
                }
            };

            volleyQueue.add(request);
        }
        else {
            //TODO: Add an error message for value being less than 0...
        }
    }
}

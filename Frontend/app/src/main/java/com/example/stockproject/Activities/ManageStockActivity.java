package com.example.stockproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stockproject.R;

public class ManageStockActivity extends AppCompatActivity {
    private Button homeButton, stocksButton, buyStocks, sellStocks;
    private TextView stockName, stockPrice;
    private ImageView change;
    private String currentUser, currentStock, value;
    private int currentChange;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managestock);

        currentUser = getIntent().getStringExtra("username");
        value = getIntent().getStringExtra("value");
        currentStock = getIntent().getStringExtra("stockName");
        currentChange = getIntent().getIntExtra("change", 0);
        homeButton = (Button) findViewById(R.id.home_button7);
        stocksButton = (Button) findViewById(R.id.stocks_button);
        buyStocks = (Button) findViewById(R.id.buyStocks);
        sellStocks = (Button) findViewById(R.id.sellStocks);
        stockName = (TextView) findViewById(R.id.stkName);
        stockPrice = (TextView) findViewById(R.id.price);
        change = (ImageView) findViewById(R.id.changeInPrice);

        stockName.setText(currentStock);
        stockPrice.setText(value);
        change.setImageResource(currentChange);
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
                //TODO: Make a POST Request using userID, numStocks, and typeStock
            }
        });

        sellStocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Make a POST Request using userID, numStocks, and typeStock
            }
        });
    }
}

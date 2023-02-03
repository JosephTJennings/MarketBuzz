package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CounterActivity extends AppCompatActivity {

    Button backBtn;
    TextView purchaseHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        backBtn = findViewById(R.id.backBtn);
        purchaseHistory = findViewById(R.id.purchaseHistory);
        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            String ticker = extras.getString("ticker");
            int numBought = extras.getInt("numShares");
            String purchaseStatement = String.format("You purchased %d shares of %s!", numBought, ticker);
            purchaseHistory.setText(purchaseStatement);
        }
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(CounterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
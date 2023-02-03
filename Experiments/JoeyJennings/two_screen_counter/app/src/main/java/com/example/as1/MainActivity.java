package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText numBought;
    EditText inputTicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.toCounterBtn);
        inputTicker = findViewById(R.id.tickerInput);
        numBought = findViewById(R.id.numShareInput);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, CounterActivity.class);
                intent.putExtra("numShares", Integer.parseInt(numBought.getText().toString()));
                intent.putExtra("ticker", inputTicker.getText().toString());
                startActivity(intent);
            }
        });


    }


}
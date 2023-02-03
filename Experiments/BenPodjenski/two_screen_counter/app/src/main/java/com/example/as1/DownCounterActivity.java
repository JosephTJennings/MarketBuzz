package com.example.as1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DownCounterActivity extends AppCompatActivity {

    Button decreaseBtn;
    Button backBtn;
    TextView numberTxt;

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_counter);

        decreaseBtn = findViewById(R.id.decreaseBtn);
        backBtn = findViewById(R.id.backBtn);
        numberTxt = findViewById(R.id.number);

        decreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                numberTxt.setText(String.valueOf(counter -= 1));
            }
        });


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(DownCounterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}

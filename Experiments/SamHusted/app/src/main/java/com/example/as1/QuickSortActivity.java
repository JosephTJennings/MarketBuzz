package com.example.as1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class QuickSortActivity extends AppCompatActivity {
    
    Button backBtn;
    Button randomBtn;
    TextView numberTxt;
    Random rd = new Random();

    int counter = 0;
    int[] sortArr = new int[10];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        backBtn = findViewById(R.id.backBtn);
        randomBtn = findViewById(R.id.increaseBtn);
        numberTxt = findViewById(R.id.number);

        randomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String s = "";
                for (int i = 0; i < 10; i++) {
                    sortArr[i] = rd.nextInt();
                    s = s + String.valueOf(sortArr[i]) + "\t";
                }
                numberTxt.setText(s);
            }

        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(QuickSortActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}

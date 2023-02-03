package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class CounterActivity extends AppCompatActivity {

    Button increaseBtn;
    Button backBtn;
    Button randomBtn;
    TextView numberTxt;
    TextView guessNum;
    TextView guessText;
    Random rd = new Random();
    int counter = 0;
    int gNum;
    int rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        increaseBtn = findViewById(R.id.increaseBtn);
        backBtn = findViewById(R.id.backBtn);
        randomBtn = findViewById(R.id.randomBtn);
        numberTxt = findViewById(R.id.number);
        guessNum = findViewById(R.id.guessNum);
        guessText = findViewById(R.id.guessText);

        increaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                numberTxt.setText(String.valueOf(++counter));
            }
        });


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(CounterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        randomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                rand = rd.nextInt(10);
                numberTxt.setText(String.valueOf(rand));
                gNum = Integer.valueOf(guessNum.getText().toString());
                if (gNum == rand) {
                    guessText.setText("Good Job!");
                }
                else guessText.setText("Try again.");
            }
        });



    }
}
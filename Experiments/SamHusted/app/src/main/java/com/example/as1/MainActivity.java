package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button1;
    // Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.toCounterBtn);
        // button2 = findViewById(R.id.toQuickSortBtn);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, CounterActivity.class);
                startActivity(intent);
            }
        });

        // button2.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View v)
        //     {
        //         Intent intent = new Intent(MainActivity.this, QuickSortActivity.class);
        //         startActivity(intent);
        //     }
        // });



    }


}
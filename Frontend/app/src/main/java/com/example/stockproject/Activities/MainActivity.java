package com.example.stockproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.stockproject.R;

public class MainActivity extends AppCompatActivity {
    private Button regButton;
    private TextView regText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        regButton = (Button) findViewById(R.id.RegisterButton);
        regText = (TextView) findViewById(R.id.RegisterText);

        //button click listeners
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this,
                        RegisterActivity.class));
            }
        });
    }
}
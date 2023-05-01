package com.example.stockproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stockproject.R;

public class OptionsActivity extends AppCompatActivity {
    private Button HomeButton;
    private String currentUser, currentType, currentMoney;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        currentUser = getIntent().getStringExtra("username");

        HomeButton = (Button) findViewById(R.id.home_button5);
        HomeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("username", currentUser);
                intent.putExtra("type", currentType);
                intent.putExtra("money", currentMoney);
                //System.out.println("received and passing back: " + currentUser);
                startActivity(intent);
            }
        });
    }
}
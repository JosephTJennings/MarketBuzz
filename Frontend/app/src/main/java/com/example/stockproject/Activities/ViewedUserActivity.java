package com.example.stockproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stockproject.R;

public class ViewedUserActivity extends AppCompatActivity {
    private Button HomeButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vieweduser);
        TextView userName = findViewById(R.id.text_username);
        userName.setText(getIntent().getStringExtra("targetUser"));

        HomeButton = (Button) findViewById(R.id.home_button01);
        HomeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //System.out.println("received and passing back: " + currentUser);
                startActivity(intent);
            }
        });
    }
}

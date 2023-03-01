package com.example.stockproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stockproject.R;

public class HomeActivity  extends AppCompatActivity {
    String currentUser;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //currentUser = getIntent().getStringExtra("username");
        currentUser = "TestUser123";

        Button followersButton = (Button) findViewById(R.id.followers_page);
        followersButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FollowersActivity.class);
                intent.putExtra("username", currentUser);
                //System.out.println("received and passing back: " + currentUser);
                startActivity(intent);
            }
        });

    }
}

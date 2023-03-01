package com.example.stockproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.stockproject.R;

public class MainActivity extends AppCompatActivity {

    private Button leaderboard;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String currentUser;

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
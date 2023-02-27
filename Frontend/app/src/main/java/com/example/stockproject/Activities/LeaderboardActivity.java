package com.example.stockproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stockproject.R;

import org.json.JSONArray;
import org.json.JSONObject;

import app.server.Request;

public class LeaderboardActivity extends AppCompatActivity {
    private TextView stats;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        stats = (TextView) findViewById(R.id.Leaderboard);
        Request.get("/people", this::addStats, null);
    }
    //Add the json file to textView stats
    private void addStats(JSONArray response) {
        String resp;
        resp = response.toString();
        System.out.println(resp);
    }
}

package com.example.stockproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LeaderboardActivity extends AppCompatActivity implements recyclerView_interface {
    private TextView stats;
    ArrayList<UsersModel> users = new ArrayList<>();
    private Button HomeButton;
    private RecyclerView recyclerView;
    private TextView Leaderboard;
    private RequestQueue volleyQueue;
    private ImageButton RefreshButton;
    private String currentUser, currentType, currentMoney, currentValuation;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
//        Request.get("/people", this::addStats, null);
        HomeButton = (Button) findViewById(R.id.home_button6);
        RefreshButton = (ImageButton) findViewById(R.id.refresh_leaderboard);
        recyclerView = (RecyclerView) findViewById(R.id.stocksRecycler);
        Leaderboard = (TextView) findViewById(R.id.LeaderboardText);
        volleyQueue = Volley.newRequestQueue(LeaderboardActivity.this);
        currentUser = getIntent().getStringExtra("username");
        currentType = getIntent().getStringExtra("type");
        currentMoney = getIntent().getStringExtra("money");
        currentValuation = getIntent().getStringExtra("valuation");
        if (currentUser == null) {
            currentUser = "srhusted";
        }
        if (currentMoney == null) {
            currentMoney = "$1000.00";
        }
        if (currentType == null) {
            currentType = "Admin";
        }
        if (currentValuation == null) {
            currentValuation = "$1000.00";
        }
        HomeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //System.out.println("received and passing back: " + currentUser);
                intent.putExtra("username", currentUser);
                intent.putExtra("type", currentType);
                intent.putExtra("money", currentMoney);
                startActivity(intent);
            }
        });
        setUsersModels();

        RefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUsersModels();
            }
        });
    }

    public void setUsersModels() {
        users.clear();
        //volleyQueue = Volley.newRequestQueue(FollowersActivity.this);
        String url = "http://coms-309-019.class.las.iastate.edu:8080/leaderboard";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject user = response.getJSONObject(i);
                                System.out.println("JSON object received");
                                String pos = Integer.toString(i);
                                String username = user.getString("username");
                                String valuation = String.format("$%.2f", user.getDouble("totalValue"));
                                double change = 0;
                                UsersModel userMod;
                                if (change > 0) userMod = new UsersModel(username, pos, valuation, R.drawable.baseline_arrow_upward_24);
                                else if (change < 0) userMod = new UsersModel(username, pos, valuation, R.drawable.baseline_arrow_downward_24);
                                else userMod = new UsersModel(username, pos, valuation, R.drawable.baseline_neutral_24);
                                users.add(userMod);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        refreshRecyclerView();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        volleyQueue.add(request);
    }
    public void refreshRecyclerView() {
        usr_recyclerView_adapter adapter = new usr_recyclerView_adapter(LeaderboardActivity.this, users, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(LeaderboardActivity.this));
    }

    @Override
    public void onItemClick(int position) {
        Intent user;
        if (users.get(position).getUsername().compareTo(currentUser) == 0) {
            user = new Intent(getApplicationContext(), ProfileActivity.class);
        }
        else {
            user = new Intent(getApplicationContext(), ViewedUserActivity.class);
            user.putExtra("viewedUser", users.get(position).getUsername());
        }
        user.putExtra("username", currentUser);
        user.putExtra("type", currentType);
        user.putExtra("money", currentMoney);
        user.putExtra("valuation", currentValuation);
        startActivity(user);
    }
}

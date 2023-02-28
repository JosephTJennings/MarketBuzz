package com.example.stockproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FollowersActivity  extends AppCompatActivity{
    SearchView search_bar;
    ArrayList<FollowersModel> availableUsers = new ArrayList<FollowersModel>();
    RecyclerView recyclerView;
    String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        setFollowersModels();

        currentUser = getIntent().getStringExtra("username");

        Button homeButton = findViewById(R.id.home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("username", currentUser);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recycle_followers);
        fol_recyclerView_adapter adapter = new fol_recyclerView_adapter(this, availableUsers);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        search_bar = findViewById(R.id.searchBar);
        search_bar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                int success = addFollowers(s);
                if(success == 0) {
                    Toast followerAble = Toast.makeText(getApplicationContext(), "Successful follow!", Toast.LENGTH_SHORT + 1);
                    followerAble.show();
                } else if(success == 1) {
                    Toast error = Toast.makeText(getApplicationContext(), "Requested user does not exist.", Toast.LENGTH_SHORT + 1);
                    error.show();
                } else if(success == 2) {
                    Toast error = Toast.makeText(getApplicationContext(), "Already following user.", Toast.LENGTH_SHORT + 1);
                    error.show();
                }
                System.out.println(success);
                return success == 0;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }
    public void setFollowersModels() {
        // THIS IS WHERE A GET HTTP REQUEST WILL BE
        RequestQueue volleyQueue = Volley.newRequestQueue(FollowersActivity.this);
        String url = "http://localhost:8080/following";

        JsonArrayRequest getArray = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject responseObj = response.getJSONObject(i);
                        FollowersModel following = new FollowersModel(responseObj.getString("username"), R.drawable.user_follow);
                        availableUsers.add(following);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FollowersActivity.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
        volleyQueue.add(getArray);
    }
    public int addFollowers(String usernameToFollow) {
        // returns 0 if successful, 1 if the requested user does not exist, and 2 if user already follows them

        return 0; // return successful
    }


}

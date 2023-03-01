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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockproject.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FollowersActivity  extends AppCompatActivity{
    private SearchView search_bar;
    private ArrayList<FollowersModel> availableUsers = new ArrayList<>();
    private  RecyclerView recyclerView;
    private String currentUser;
    private RequestQueue volleyQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);

        currentUser = getIntent().getStringExtra("username");
        recyclerView = findViewById(R.id.recycle_followers);

        Button homeButton = findViewById(R.id.home_button);
        Button refreshButton = findViewById(R.id.refresh_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("username", currentUser);
                startActivity(intent);
            }
        });

        search_bar = findViewById(R.id.searchBar);
        search_bar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                int success = addFollowers(s);
                if(success == 0) {
                    String followMessage = "Successfully followed " + s + "!";
                    Toast followerAble = Toast.makeText(getApplicationContext(), followMessage, Toast.LENGTH_SHORT + 1);
                    followerAble.show();
                }
                search_bar.clearFocus();
                search_bar.setQuery("", false);
                return success == 0;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        setFollowersModels();

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshRecyclerView();
            }
        });
        refreshRecyclerView();
    }
    public void setFollowersModels() {
        volleyQueue = Volley.newRequestQueue(FollowersActivity.this);
        String url = "https://414ff111-04c7-445e-a169-652f4de6f117.mock.pstmn.io/following";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
        new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject following = response.getJSONObject(i);
                        String followingUsername = following.getString("username");
                        FollowersModel follower = new FollowersModel(followingUsername, R.drawable.user_follow);
                        availableUsers.add(follower);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
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
        fol_recyclerView_adapter adapter = new fol_recyclerView_adapter(FollowersActivity.this, availableUsers);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(FollowersActivity.this));
    }
    public int addFollowers(String usernameToFollow) {
        // returns 0 if successful, 1 if the requested user does not exist, and 2 if user already follows them
        String url = "https://414ff111-04c7-445e-a169-652f4de6f117.mock.pstmn.io/followingRequest";
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String successMessage = "Followed user " + usernameToFollow + "!";
                Toast.makeText(FollowersActivity.this, successMessage, Toast.LENGTH_SHORT + 1).show();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FollowersActivity.this, "Error, unable to follow user.", Toast.LENGTH_SHORT + 1).show();
            }
        }) {
            @Override
            protected HashMap<String, String> getParams() {
                HashMap<String, String> params = new HashMap<>();
                System.out.println(currentUser + " -> " + usernameToFollow);
                params.put("username", currentUser);
                params.put("following", usernameToFollow);
                return params;
            }
        };

        volleyQueue.add(request);
        return 0; // return successful
    }


}
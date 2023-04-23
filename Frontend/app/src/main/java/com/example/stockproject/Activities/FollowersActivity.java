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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockproject.Activities.adapter.fol_recyclerView_adapter;
import com.example.stockproject.Activities.model.FollowersModel;
import com.example.stockproject.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import app.server.CustomRequest;
/**
 * This activity is the Followers Activity. This activity presents a list of users the user can choose from.
 */
public class FollowersActivity  extends AppCompatActivity{
    private SearchView search_bar;
    private ArrayList<FollowersModel> availableUsers = new ArrayList<>();
    private RecyclerView recyclerView;
    private String currentUser;
    private RequestQueue volleyQueue;

    /**
     * This method will create all the buttons, textViews, and Strings for the current Activity and set
     * each button to navigate to their corresponding activities.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);

        currentUser = getIntent().getStringExtra("username");
        recyclerView = findViewById(R.id.recycle_followers);
        volleyQueue = Volley.newRequestQueue(FollowersActivity.this);

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
    /**
     * This method will produce a POST Request add a user to the followingList if the user exists.
     */
    public void setFollowersModels() {
        //volleyQueue = Volley.newRequestQueue(FollowersActivity.this);
        String url = "http://coms-309-019.class.las.iastate.edu:8080/following/people";
        HashMap<String, String> params = new HashMap<>();
        params.put("username", currentUser);
        JSONObject obj = new JSONObject(params);
        CustomRequest request = new CustomRequest(Request.Method.POST, url, obj,
        new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject following = response.getJSONObject(i);
                        System.out.println("JSON object received");
                        String followingUsername = following.getString("followingUser");
                        FollowersModel follower = new FollowersModel(followingUsername, R.drawable.user_follow);
                        availableUsers.add(follower);
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
    /**
     * This method will refresh the recyclerView.
     */
    public void refreshRecyclerView() {
        fol_recyclerView_adapter adapter = new fol_recyclerView_adapter(FollowersActivity.this, availableUsers);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(FollowersActivity.this));
    }

    /**
     * This method checks if the user exists and if the relationship between you and the user exists.
     * @param usernameToFollow The name of the user
     * @return 0 if successful, 1 if the requested user does not exist, and 2 if user already follows them
     */
    public int addFollowers(String usernameToFollow) {
        String url = "http://coms-309-019.class.las.iastate.edu:8080/following/post";
        HashMap<String, String> params = new HashMap<>();
        params.put("username", currentUser);
        params.put("followingUser", usernameToFollow);
        JSONObject obj = new JSONObject(params);
        //volleyQueue = Volley.newRequestQueue(FollowersActivity.this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, obj, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String successMessage = "Followed user " + usernameToFollow + "!";
                Toast.makeText(FollowersActivity.this, successMessage, Toast.LENGTH_SHORT + 1).show();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FollowersActivity.this, "Error, unable to follow user.", Toast.LENGTH_SHORT + 1).show();
            }
        });

        volleyQueue.add(request);
        return 0; // return successful
    }


}
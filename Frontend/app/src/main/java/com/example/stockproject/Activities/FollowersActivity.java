package com.example.stockproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockproject.R;

import java.util.ArrayList;

public class FollowersActivity  extends AppCompatActivity implements SearchView.OnQueryTextListener{
    SearchView followSearch;
    ArrayList<FollowersModel> availableUsers = new ArrayList<FollowersModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        setFollowersModels();

        Button followersButton = (Button) findViewById(R.id.home_button);
        followersButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_home);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycle_followers);
        fol_recyclerView_adapter adapter = new fol_recyclerView_adapter(this, availableUsers);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //followSearch = (SearchView) findViewById(R.id.searchbar_request_follow);
        //followSearch.clearFocus();
    }
    public void setFollowersModels() {
        // THIS IS WHERE A GET HTTP REQUEST WILL BE
        FollowersModel f1 = new FollowersModel("Ben", R.drawable.user_follow);
        FollowersModel f2 = new FollowersModel("Joey", R.drawable.user_follow);
        FollowersModel f3 = new FollowersModel("Sam", R.drawable.user_follow);
        FollowersModel f4 = new FollowersModel("Adam", R.drawable.user_follow);
        FollowersModel f5 = new FollowersModel("Samantha", R.drawable.user_follow);
        FollowersModel f6 = new FollowersModel("Jodie", R.drawable.user_follow);
        for(int i = 0; i < 2; i++) {
            availableUsers.add(f1);
            availableUsers.add(f2);
            availableUsers.add(f3);
            availableUsers.add(f4);
            availableUsers.add(f5);
            availableUsers.add(f6);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

}

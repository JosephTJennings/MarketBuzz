package com.example.stockproject.Activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.stockproject.R;

import java.util.ArrayList;

public class FollowersActivity  extends AppCompatActivity implements SearchView.OnQueryTextListener{
    SearchView followSearch;
    ArrayList<String> availableUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        availableUsers.add("Ben");
        availableUsers.add("Joey");
        availableUsers.add("Sam");
        followSearch = (SearchView) findViewById(R.id.searchbar_request_follow);

        Intent intent = getIntent();
        if(Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

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

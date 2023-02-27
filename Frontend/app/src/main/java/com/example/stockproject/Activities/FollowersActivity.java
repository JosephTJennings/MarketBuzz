package com.example.stockproject.Activities;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockproject.R;

import java.util.ArrayList;

public class FollowersActivity  extends AppCompatActivity{
    SearchView search_bar;
    ArrayList<FollowersModel> availableUsers = new ArrayList<FollowersModel>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        setFollowersModels();

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
    public int addFollowers(String usernameToFollow) {
        // returns 0 if successful, 1 if the requested user does not exist, and 2 if user already follows them

        return 0; // return successful
    }


}

package com.example.stockproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stockproject.R;

/**
 * This is the Options Activity. This activity is meant for changing settings or logging out.
 */
public class OptionsActivity extends AppCompatActivity {
    private Button HomeButton;
    private String currentUser;

    /**
     * This method will create all the buttons, textViews, and Strings for the current Activity and set
     * each button to navigate to their corresponding activities.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        currentUser = getIntent().getStringExtra("username");

        HomeButton = (Button) findViewById(R.id.home_button5);
        HomeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("username", currentUser);
                //System.out.println("received and passing back: " + currentUser);
                startActivity(intent);
            }
        });
    }
}
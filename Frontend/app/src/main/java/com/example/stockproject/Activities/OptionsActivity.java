package com.example.stockproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockproject.Activities.model.StocksModel;
import com.example.stockproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import app.server.Const;
import app.utils.BasicUtils;

/**
 * This is the Options Activity. This activity is meant for changing settings or logging out.
 */
public class OptionsActivity extends AppCompatActivity {
    private Button HomeButton, DeleteButton;
    private EditText deleteUser;
    private String currentUser, currentType, currentMoney, currentValuation;
    private RequestQueue volleyQueue;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        currentUser = getIntent().getStringExtra("username");
        currentType = getIntent().getStringExtra("type");
        currentMoney = getIntent().getStringExtra("money");


        if (currentUser == null) {
            currentUser = "srhusted";
        }
        if (currentMoney == null) {
            currentMoney = "$1000.00";
        }
        if (currentType == null) {
            currentType = "Admin";
        }
        volleyQueue = Volley.newRequestQueue(OptionsActivity.this);
        deleteUser = (EditText) findViewById(R.id.deletedUser);
        DeleteButton = (Button) findViewById(R.id.DeleteUserButton);
        HomeButton = (Button) findViewById(R.id.home_button5);
        HomeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("username", currentUser);
                intent.putExtra("type", currentType);
                intent.putExtra("money", currentMoney);
                intent.putExtra("valuation", currentValuation);
                //System.out.println("received and passing back: " + currentUser);
                startActivity(intent);
            }
        });
        DeleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (String.valueOf(deleteUser.getText()).equals(currentUser)) {
                    String successMessage = "Cannot delete current user " + currentUser + ".";
                    Toast.makeText(OptionsActivity.this, successMessage, Toast.LENGTH_SHORT + 1).show();
                }
                else deleteUser();
            }
        });
    }
    private void deleteUser() {
        String delUser = String.valueOf(deleteUser.getText());
        String url = Const.URL + "/person/delete/" + delUser;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, url, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.getBoolean("deleted") == true) {
                            String successMessage = "Deleted " + String.valueOf(deleteUser.getText()) + ".";
                            Toast.makeText(OptionsActivity.this, successMessage, Toast.LENGTH_SHORT + 1).show();
                        }
                        else {
                            String successMessage = "Unable to delete " + String.valueOf(deleteUser.getText()) + ".";
                            Toast.makeText(OptionsActivity.this, successMessage, Toast.LENGTH_SHORT + 1).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
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
}
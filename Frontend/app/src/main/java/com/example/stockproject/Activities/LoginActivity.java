package com.example.stockproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.server.Const;
import app.utils.BasicUtils;
/**
 * This activity is the Login Activity. This activity will be the first activity the user will start.
 */
public class LoginActivity extends AppCompatActivity {
    private String type, user, password;
    private Button regButton, loginButton, loginButton2;
    private TextView registerText, usernameText, passwordText, errorText, guestText;
    private RequestQueue volleyQueue;
    @Override
    /**
     * This method will create all the buttons, textViews, and Strings for the current Activity and set
     * each button to navigate to their corresponding activities.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent i = getIntent();
//        type = i.getStringExtra("type");
        regButton = (Button) findViewById(R.id.RegisterButton);
        registerText = (TextView) findViewById(R.id.RegisterText);
        loginButton = (Button) findViewById(R.id.LoginButton);
        loginButton2 = (Button) findViewById(R.id.LoginButton2);
        loginButton.setOnClickListener(this::attemptLogin);
        registerText = (TextView) findViewById(R.id.RegisterText);
        guestText = (TextView) findViewById(R.id.guestText);
        usernameText = (EditText) findViewById(R.id.UsernameInput);
        passwordText = (EditText) findViewById(R.id.PasswordInput);
        errorText = (TextView) findViewById(R.id.ErrorText);

        //button click listeners
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(LoginActivity.this,
                        RegisterActivity.class));
            }
        });
        loginButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent user = new Intent(LoginActivity.this, MainActivity.class);
                user.putExtra("username", "Guest");
                user.putExtra("type", "Guest");
                startActivity(user);
            }
        });
    }

    /**
     * This method will attempt a login and send a JSON Object to login if successful
     * @param v - The current view
     */
    private void attemptLogin(View v) {
//        Intent main = new Intent(this, MainActivity.class);
//        startActivity(main);
//        System.out.print(type);
        volleyQueue = Volley.newRequestQueue(LoginActivity.this);
        user = String.valueOf(usernameText.getText());
        password = String.valueOf(passwordText.getText());
        ArrayList<String> credentials = new ArrayList<String>();
        credentials.add(user);
        credentials.add(password);
        if (BasicUtils.isValidField(credentials)) return;
        Map<String, String> map = new HashMap<>();
        map.put("username", user);
        map.put("password", password);
        JSONObject obj = new JSONObject(map);
//        Request.post("/people/authenticate", obj, this::login, null);
        JsonObjectRequest request = new JsonObjectRequest(com.android.volley.Request.Method.POST, Const.URL + "/people/authenticate", obj, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String successMessage = "User " + user + " has logged in!";
                Toast.makeText(LoginActivity.this, successMessage, Toast.LENGTH_SHORT + 1).show();
                login(response);
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Error, cannot login.", Toast.LENGTH_SHORT + 1).show();
            }
        }) {
             /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        volleyQueue.add(request);

//        Request.post("/people/authenticate", obj, this::login, null);
    }

    /**
     * This method will read the JSON Object from method attemptLogin
     * @param response - A JSON Object from method attemptLogin
     */
    private void login(JSONObject response) {
            String resp;
        try {
            resp = (String) response.get("message");
            if (resp.equals("failure")) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                return;
            }
            //TODO: Add a switch case for all the different types of users
            Intent main = new Intent(this, MainActivity.class);
            main.putExtra("username", user);
//            main.putExtra("password", password);
            startActivity(main);
        }
        catch (Exception e) {
            Log.d("debug", e.toString());
            return;
        }
    }
}
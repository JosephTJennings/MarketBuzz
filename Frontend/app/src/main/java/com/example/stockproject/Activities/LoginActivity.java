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

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.server.Request;
import app.utils.BasicUtils;

public class LoginActivity extends AppCompatActivity {
    private String type, user, password;
    private Button regButton, loginButton;
    private TextView registerText, usernameText, passwordText, errorText;
    private RequestQueue volleyQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent i = getIntent();
//        type = i.getStringExtra("type");
        regButton = (Button) findViewById(R.id.RegisterButton);
        registerText = (TextView) findViewById(R.id.RegisterText);
        loginButton = (Button) findViewById(R.id.LoginButton);
        loginButton.setOnClickListener(this::attemptLogin);
        registerText = (TextView) findViewById(R.id.RegisterText);
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
    }
    private void attemptLogin(View v) {
        volleyQueue = Volley.newRequestQueue(this);
        volleyQueue.start();
        user = String.valueOf(usernameText.getText());
        password = String.valueOf(passwordText.getText());
        ArrayList<String> credentials = new ArrayList<String>();
        credentials.add(user);
        credentials.add(password);
        if (BasicUtils.isValidField(credentials)) return;
        String url = "http://localhost:8080/people/authenticate";
        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response + " FUCK");
                Toast.makeText(LoginActivity.this, "User was able to login.", Toast.LENGTH_SHORT + 1).show();
                login(response);
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print("FUCKING SHITBALLSS");
                Toast.makeText(LoginActivity.this, "Error, unable to login.", Toast.LENGTH_SHORT + 1).show();
                return;
            }
        }) {
            @Override
            protected HashMap<String, String> getParams() {
                HashMap<String, String> params = new HashMap<>();
//                    System.out.println(currentUser + " -> " + usernameToFollow);
                params.put("username", user);
                params.put("password", password);
                return params;
            }
        };
        volleyQueue.add(request);
//        Intent main = new Intent(this, MainActivity.class);
//        startActivity(main);
//        System.out.print(type);
//        Map<String, String> map = new HashMap<>();
//        map.put("username", user);
//        map.put("password", password);
//        JSONObject obj = new JSONObject(map);
//        System.out.print("attempt to hit db");
//        Request.post("/person/authenticate", obj, this::login, null);

    }

    private void login(String response) {
//        System.out.print(response.toString());
        try {
//            resp = (String) response.get("message");
            if (response.equals("failure")) {
//                Toast.makeText(getApplicationContext(), (String)response.get("error"), Toast.LENGTH_SHORT).show();
                return;
            }
            //TODO: Add a switch case for all the different types of users
            Intent main = new Intent(this, MainActivity.class);
            main.putExtra("username", user);
            main.putExtra("password", password);
            startActivity(main);
        }
        catch (Exception e) {
            Log.d("debug", e.toString());
            return;
        }
    }
    private boolean findUser(String user, String password) {
        return true;
    }
}
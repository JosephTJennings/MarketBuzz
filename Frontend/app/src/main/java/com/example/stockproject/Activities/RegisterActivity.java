package com.example.stockproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
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
 * This activity is the Login Activity. This activity can only be activated from LoginActivity.
 * This activity attempts to create a user from the information provided by the user.
 */
public class RegisterActivity extends AppCompatActivity {
    private String TAG = RegisterActivity.class.getSimpleName();
    private Button rButton;
    private EditText usernameInput, passwordInput;
    private TextView errorRegister;
    private String user, password, userType;
    private RequestQueue volleyQueue;
    private Boolean isAdmin;
    private Switch userSwitch;
    @Override
    /**
     * This method will create all the buttons, textViews, and Strings for the current Activity and set
     * each button to navigate to their corresponding activities.
     */
    protected void onCreate(Bundle savedInstanceState) {
        volleyQueue = Volley.newRequestQueue(RegisterActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rButton = (Button) findViewById(R.id.registerButton);
        rButton.setOnClickListener(this::attemptCreateUser);
        usernameInput = (EditText) findViewById(R.id.usernameInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        errorRegister = (TextView) findViewById(R.id.RegisterError);
        userSwitch = (Switch) findViewById(R.id.userOrAdmin);

    }
    /**
     * This method will check if the username the user provided exists. If it does, it prompts the user
     * to change their username.
     * @param v a View
     */
    private void attemptCheckUsername(View v) {
        user = String.valueOf(usernameInput.getText());
        Map<String, String> map = new HashMap<>();
        map.put("username", user);
        JSONObject obj = new JSONObject(map);
        JsonObjectRequest request = new JsonObjectRequest(com.android.volley.Request.Method.POST, Const.URL + "people/authenticate/register", obj, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String resp;
                try {
                    resp = (String) response.get("message");
                    if (resp.equals("failure")) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String successMessage = "There are no users named " + user + "!";
                    Toast.makeText(RegisterActivity.this, successMessage, Toast.LENGTH_SHORT + 1).show();
                    attemptCreateUser(v);
                }
                catch (Exception e) {
                    Log.d("debug", e.toString());
                    return;
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, "Error, user already exists.", Toast.LENGTH_SHORT + 1).show();
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

    }
    /**
     * This method will send a POST Request to create the user in the database.
     * @param v a View
     */
    private void attemptCreateUser(View v) {
        user = String.valueOf(usernameInput.getText());
        password = String.valueOf(passwordInput.getText());
        isAdmin = userSwitch.isChecked();
        userType = (isAdmin ? "Admin" : "User");
        ArrayList<String> credentials = new ArrayList<String>();
        credentials.add(user);
        credentials.add(password);
        if (BasicUtils.isValidField(credentials)) return;
        Map<String, String> map = new HashMap<>();
        map.put("username", user);
        map.put("password", password);
        map.put("type", userType);
        JSONObject obj = new JSONObject(map);
        JsonObjectRequest request = new JsonObjectRequest(com.android.volley.Request.Method.POST, Const.URL + "/people/post", obj, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String successMessage = "User " + user + " has logged in!";
                Toast.makeText(RegisterActivity.this, successMessage, Toast.LENGTH_SHORT + 1).show();
                login(response);
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, "Error, unable to follow user.", Toast.LENGTH_SHORT + 1).show();
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
     * This method will login based off of the JSON Object in the parameters
     * @param response A JSON Object sent from AttemptCreateUser
     */
    private void login(JSONObject response) {
        try {
            if (response.get("pid") == null) {
                Toast.makeText(getApplicationContext(), (String)response.get("error"), Toast.LENGTH_SHORT).show();
                return;
            }
            //TODO: Add a switch case for all the different types of users
            Intent main = new Intent(this, MainActivity.class);
            main.putExtra("username", user);
            startActivity(main);
        }
        catch (Exception e) {
            Log.d("debug", e.toString());
            return;
        }
    }
}
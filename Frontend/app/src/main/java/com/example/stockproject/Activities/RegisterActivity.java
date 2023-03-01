package com.example.stockproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import app.server.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.stockproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.AppController;
import app.User;
import app.UserController;
import app.server.Const;
import app.utils.BasicUtils;

public class RegisterActivity extends AppCompatActivity {
    private String TAG = RegisterActivity.class.getSimpleName();
    private Button rButton;
    private EditText usernameInput, passwordInput, passwordCheckInput, firstNameInput, lastNameInput;
    private TextView errorRegister;
    private String user, password, checkPassword, firstName, lastName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rButton = (Button) findViewById(R.id.registerButton);
        rButton.setOnClickListener(this::attemptCreateUser);
        usernameInput = (EditText) findViewById(R.id.usernameInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        firstNameInput = (EditText) findViewById(R.id.firstNameInput);
        lastNameInput = (EditText) findViewById(R.id.lastNameInput);
        passwordCheckInput = (EditText) findViewById(R.id.passwordCheckInput);
        errorRegister = (TextView) findViewById(R.id.RegisterError);
    }

    private void attemptCreateUser(View v) {
        user = String.valueOf(usernameInput.getText());
        password = String.valueOf(passwordInput.getText());
        checkPassword = String.valueOf(passwordCheckInput.getText());
        firstName = String.valueOf(firstNameInput.getText());
        lastName = String.valueOf(lastNameInput.getText());
        if (!password.equals(checkPassword)) {}
        ArrayList<String> credentials = new ArrayList<String>();
        credentials.add(user);
        credentials.add(password);
        if (BasicUtils.isValidField(credentials)) return;
        Map<String, String> map = new HashMap<>();
        map.put("username", user);
        map.put("password", password);
        map.put("firstName", firstName);
        map.put("lastName", lastName);
        JSONObject obj = new JSONObject(map);
        Request.post("/people/post", obj, this::login, null);
    }

    private void login(JSONObject response) {
        String resp;
        try {
            resp = (String) response.get("message");
            if (resp.equals("failure")) {
                Toast.makeText(getApplicationContext(), (String)response.get("error"), Toast.LENGTH_SHORT).show();
                return;
            }
            //TODO: Add a switch case for all the different types of users
            Intent main = new Intent(this, MainActivity.class);
            main.putExtra("username", user);
            main.putExtra("password", password);
            main = new Intent(this, MainActivity.class);
            startActivity(main);
        }
        catch (Exception e) {
            Log.d("debug", e.toString());
            return;
        }
    }
}
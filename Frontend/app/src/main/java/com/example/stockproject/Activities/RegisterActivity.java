package com.example.stockproject.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.stockproject.R;

import org.json.JSONException;

import app.AppController;
import app.User;
import app.UserController;
import app.server.Const;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = RegisterActivity.class.getSimpleName();
    private Button rButton;
    private EditText usernameInput;
    private EditText passwordInput;
    private EditText passwordCheckInput;
    private TextView errorRegister;
    private UserController usrCtrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rButton = (Button) findViewById(R.id.registerButton);
        usernameInput = (EditText) findViewById(R.id.usernameInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        passwordCheckInput = (EditText) findViewById(R.id.passwordCheckInput);
        errorRegister = (TextView) findViewById(R.id.RegisterError);

        //button click listeners
        rButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.registerButton) {
            System.out.println("Button has been pressed.");
            try {
                makeJsonUsrReq();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void makeJsonUsrReq() throws JSONException {
        String username = String.valueOf(usernameInput.getText());
        String password = String.valueOf(passwordInput.getText());
        String confirm = String.valueOf(passwordCheckInput.getText());

        if (password.equals(confirm)) {
            errorRegister.setText("User passwords are the same. Adding account...");
            //Check if the user has not been a user before
            int id = checkUser(username);
            User u = new User(id, username, password, "User");
            usrCtrl.addUser(u);

        }
        else {
            errorRegister.setText("Both Passwords are not the same.");
        }
    }
    public int checkUser(String name) {
        int pos = -1;
        StringRequest strReq = new StringRequest(Request.Method.GET, Const.URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.print(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error: " + error.getMessage());
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, "string_req");
        return pos;
    }
}

package com.example.stockproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.stockproject.R;

public class LoginActivity extends AppCompatActivity {
    private Button regButton, loginButton;
    private TextView registerText, usernameText, passwordText, errorText;
    private EditText username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        regButton = (Button) findViewById(R.id.RegisterButton);
        registerText = (TextView) findViewById(R.id.RegisterText);
        loginButton = (Button) findViewById(R.id.LoginButton);
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
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String user = String.valueOf(usernameText.getText());
                String password = String.valueOf(passwordText.getText());
                boolean foundUser = findUser(user, password);
                if (foundUser) {
                    startActivity(new Intent(LoginActivity.this,
                            MainActivity.class));
                }
                else {

                }

            }
        });
    }
    private boolean findUser(String user, String password) {
        return true;
    }
}
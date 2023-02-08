package com.example.stockproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private Button rButton;
    private EditText usernameInput;
    private EditText passwordInput;
    private EditText passwordCheckInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rButton = (Button) findViewById(R.id.registerButton);
        usernameInput = (EditText) findViewById(R.id.usernameInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        passwordCheckInput = (EditText) findViewById(R.id.passwordCheckInput);

        // button click listeners
//        rButton.setOnClickListener(this);
    }
//    public void OnClick(View v) {
//        if (v.getId() == R.id.registerButton) {
//            makeJsonUsrReq();
//        }
//    }
}

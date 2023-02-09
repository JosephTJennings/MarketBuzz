package com.example.stockproject.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.stockproject.R;

import org.json.JSONArray;

import utils.Const;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = RegisterActivity.class.getSimpleName();
    private Button rButton;
    private EditText usernameInput;
    private EditText passwordInput;
    private EditText passwordCheckInput;
    private TextView errorRegister;


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
            makeJsonUsrReq();
        }
    }

    private void makeJsonUsrReq() {
        String user = String.valueOf(usernameInput.getText());
        String password = String.valueOf(passwordInput.getText());
        String confirm = String.valueOf(passwordCheckInput.getText());

        if (password.equals(confirm)) {
            errorRegister.setText("User passwords are the same. Adding account...");
//            JsonArrayRequest req = new JsonArrayRequest(Const.URL_JSON_REQUEST,
//                    new Response.Listener<JSONArray>() {
//                        @Override
//                        public void onResponse(JSONArray response) {
//                            Log.d(TAG, response.toString());
//                            msgResponse.setText(response.toString());
//                            hideProgressDialog();
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    VolleyLog.d(TAG, "Error: " + error.getMessage());
//                    hideProgressDialog();
//                }
//            });
        }
        else {
            errorRegister.setText("Both Passwords are not the same.");
        }
    }
}

package com.example.stockproject.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import app.server.Const;

public class MainActivity extends AppCompatActivity {

    //These are for the alert that occurs when the guest tries to press on a button that requires a user...
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView guestMessage;
    private Button returnToLogin, returnToRegister;
    private ImageView returnToMain;
    private RequestQueue volleyQueue;

    private Button leaderboard, logout, stocks, profile, followers, options;
    private TextView user, money;
    private String currentUser, currentMoney, currentType, currentValuation;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        volleyQueue = Volley.newRequestQueue(MainActivity.this);
        currentUser = getIntent().getStringExtra("username");
        currentMoney = getIntent().getStringExtra("money");
        currentType = getIntent().getStringExtra("type");
        currentValuation = getIntent().getStringExtra("valuation");
        if (currentMoney == null || currentType == null || currentValuation == null) { getUserInfo(currentUser); }
//        System.out.println(currentUser);
        leaderboard = (Button) findViewById(R.id.leaderboard_page);
        followers = (Button) findViewById(R.id.followers_page);
        logout = (Button) findViewById(R.id.logout_page);
        stocks = (Button) findViewById(R.id.stocks_page);
        profile = (Button) findViewById(R.id.profile_page);
        options = (Button) findViewById(R.id.options_page);
        user = (TextView) findViewById(R.id.currentUser);
        //Append currentUser to user
        user.setText(currentUser);
        //TODO: Add a get request for the current funds the user has
        money = (TextView) findViewById(R.id.currentMoney);



        followers.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (checkType(currentType) == true) {
                    Intent intent = new Intent(getApplicationContext(), FollowersActivity.class);
                    intent.putExtra("username", currentUser);
                    intent.putExtra("type", currentType);
                    intent.putExtra("valuation", currentValuation);
                    //System.out.println("received and passing back: " + currentUser);
                    startActivity(intent);
                }
                else {
                    createNewContactDialog("Followers");
                    Toast.makeText(getApplicationContext(), currentUser + " is of type " + currentType + ".", Toast.LENGTH_LONG);
                }
            }
        });
        leaderboard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LeaderboardActivity.class);
                intent.putExtra("username", currentUser);
                intent.putExtra("type", currentType);
                intent.putExtra("money", currentMoney);
                intent.putExtra("valuation", currentValuation);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OptionsActivity.class);
                intent.putExtra("username", currentUser);
                intent.putExtra("type", currentType);
                intent.putExtra("money", currentMoney);
                intent.putExtra("valuation", currentValuation);
                //System.out.println("received and passing back: " + currentUser);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (checkType(currentType) == true) {
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    intent.putExtra("username", currentUser);
                    intent.putExtra("type", currentType);
                    intent.putExtra("money", currentMoney);
                    intent.putExtra("valuation", currentValuation);
                    //System.out.println("received and passing back: " + currentUser);
                    startActivity(intent);
                }
                else {
                    createNewContactDialog("Profile");
                }
            }
        });
        stocks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StocksActivity.class);
                intent.putExtra("username", currentUser);
                intent.putExtra("type", currentType);
                intent.putExtra("money", currentMoney);
                intent.putExtra("valuation", currentValuation);
                //System.out.println("received and passing back: " + currentUser);
                startActivity(intent);
            }
        });
    }
    //Checks if the user is of type Guest
    public boolean checkType(String currentType) {
        if (currentType.equals("Guest")) return false;
        else return true;
    }
    public void getUserInfo(String user)
    {
        Map<String, String> map = new HashMap<>();
        map.put("username", user);
        JSONObject obj = new JSONObject(map);
        String url = Const.URL + "/username/string/data";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            currentType = (String) response.get("type");
                            currentMoney = (String) response.get("cashValue");
                            currentValuation = (String) response.get("totalValue");
                        }
                        catch(Exception e) {
                            Log.d("debug", e.toString());
                            return;
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
    public void createNewContactDialog(String name) {
        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopup = getLayoutInflater().inflate(R.layout.popup, null);
        guestMessage = (TextView) contactPopup.findViewById(R.id.GuestMessage);
        guestMessage.setText("Cannot access " + name + " due to Guest Access. To access " + name + ", create a new account or login.");
        returnToLogin = (Button) contactPopup.findViewById(R.id.toLogin);
        returnToRegister = (Button) contactPopup.findViewById(R.id.toRegister);
        returnToMain = (ImageView) contactPopup.findViewById(R.id.toMain);

        dialogBuilder.setView(contactPopup);
        dialog = dialogBuilder.create();
        dialog.show();

        returnToLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                //System.out.println("received and passing back: " + currentUser);
                startActivity(intent);
            }
        });
        returnToRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                //System.out.println("received and passing back: " + currentUser);
                startActivity(intent);
            }
        });
        returnToMain.setOnClickListener(new View.OnClickListener(){
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
    }
}
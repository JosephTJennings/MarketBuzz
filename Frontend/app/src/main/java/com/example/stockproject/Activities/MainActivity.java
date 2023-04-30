package com.example.stockproject.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stockproject.R;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //These are for the alert that occurs when the guest tries to press on a button that requires a user...
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView guestMessage;
    private Button returnToLogin, returnToRegister;
    private ImageView returnToMain;

    private Button leaderboard, logout, stocks, profile, followers, options;
    private TextView user, money;
    private String currentUser, currentMoney, currentType;
    private JSONObject person;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentUser = getIntent().getStringExtra("username");
        currentMoney = getIntent().getStringExtra("money");
        currentType = getIntent().getStringExtra("type");
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
                //System.out.println("received and passing back: " + currentUser);
                startActivity(intent);
            }
        });
    }
}
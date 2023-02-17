package app;

import android.app.Application;
import android.util.Log;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.stockproject.Activities.RegisterActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import app.utils.Const;

public class UserController extends Application {

    private String USERTAG = UserController.class.getSimpleName();


    HashMap<String, User> userList = new HashMap<>();


    public void getAllPersons() {

        HashMap<String, User> userList = null;

    }

    public void addUser(User usr) throws JSONException {
        JSONObject newUsr = new JSONObject();
        newUsr.put("id", usr.getId());
        newUsr.put("username", usr.getUsername());
        newUsr.put("password", usr.getPassword());
        newUsr.put("type", usr.getType());

//        AppController.getInstance().addToRequestQueue(strReq, USERTAG);
    }


}

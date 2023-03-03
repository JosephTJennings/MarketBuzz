package app.server;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import app.AppController;

public class Request {
    //outputs a JSONObject
    public static void get(String route, ObjResponse success, ErrorResponse error) {
        JsonObjectRequest request = new JsonObjectRequest(GET, Const.URL + route, null, success::respond,
                response -> {
                    if (error == null) ErrorResponse.getBasic().respond(response);
                    else error.respond(response);
                }) {
            @Override public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json;charset=utf-8");
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(request, "json_obj_req");
    }

    //outputs a JSONArray
    public static void get(String route, ArrayResponse success, ErrorResponse error) {
        JsonArrayRequest request = new JsonArrayRequest(GET, Const.URL + route, null, success::respond,
                response -> {
                    if (error == null) ErrorResponse.getBasic().respond(response);
                    else error.respond(response);
                }) {
            @Override public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json;charset=utf-8");
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(request, "json_obj_req");
    }

    //JSONObject outputs a JSONObject
    public static void post(String route, JSONObject obj, ObjResponse success, ErrorResponse error) {
        JsonObjectRequest request = new JsonObjectRequest(POST, "http://localhost:8080/people/authenticate", obj, success::respond,
                response -> {
                    if (error == null) ErrorResponse.getBasic().respond(response);
                    else error.respond(response);
                }) {
            @Override public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json;charset=utf-8");
                return headers;
            }};
        AppController.getInstance().addToRequestQueue(request, "json_obj_req");
    }


    //JSONObject outputs a JSONArray
    public static void post(String route, JSONObject obj, ArrayResponse success, ErrorResponse error) {
        String str = obj.toString();
        StringRequest request = new StringRequest(POST, Const.URL + route,
                response -> {
                    try {success.respond(new JSONArray(response));
                    } catch (JSONException e) {e.printStackTrace();}
                }, response -> {
            if (error == null) ErrorResponse.getBasic().respond(response);
            else error.respond(response);
        }) {
            @Override public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
            @Override public byte[] getBody() {
                try {return str.getBytes(StandardCharsets.UTF_8);
                } catch (Exception e) {e.printStackTrace();return null;}
            }
            @Override public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json;charset=utf-8");
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(request, "json_obj_req");
    }
}

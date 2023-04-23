package app.server;

import androidx.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * This class creates a custom JSON Request.
 */
public class CustomRequest extends JsonRequest<JSONArray> {
    protected static final String PROTOCOL_CHARSET = "utf-8";

    /**
     * This constructor creates a JSON Request with these parameters
     * @param method the method
     * @param url the link to connect to
     * @param requestBody the data being sent to the url
     * @param listener the object that awaits a response
     * @param errorListener the object that awaits an error
     */
    public CustomRequest(int method, String url, @Nullable String requestBody, Response.Listener<JSONArray> listener, @Nullable Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
    }
    /**
     * This constructor creates a JSON Request with these parameters
     * @param method the method
     * @param url the link to connect to
     * @param jsonRequest the data being sent to the url
     * @param listener the object that awaits a response
     * @param errorListener the object that awaits an error
     */
    public CustomRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        super(method, url, (jsonRequest == null) ? null : jsonRequest.toString(), listener, errorListener);
    }

    /**
     * This class parses the Network Response.
     * @param response Response from the network
     * @return success or error
     */
    @Override
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONArray(jsonString), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }
}

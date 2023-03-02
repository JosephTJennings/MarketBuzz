package app.server;

import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
public interface ErrorResponse {
    void respond(VolleyError err);

    static ErrorResponse getBasic() {
        return new BasicErrorResponse();
    }

    class BasicErrorResponse implements ErrorResponse {
        public void respond(VolleyError err) {
            VolleyLog.d("Error", "Error" + err.getMessage());
        }
    }
}

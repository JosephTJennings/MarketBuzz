package app;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();
    private RequestQueue reqQueue;
    private static AppController appCtrl;
    @Override
    public void onCreate() {
        super.onCreate();
        appCtrl = this;
    }
    public RequestQueue getRequestQueue() {
        if (reqQueue == null) {
            reqQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return reqQueue;
    }
    public static synchronized AppController getInstance() {
        return appCtrl;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }
}

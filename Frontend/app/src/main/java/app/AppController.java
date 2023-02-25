package app;

import android.app.Application;
import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/*
 * @author Sam Husted
 */
public class AppController extends Application {

    /**
     * A default tag for requests
     */
    public static final String TAG = AppController.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private static AppController mInstance;

    /**
     * An onCreate method; sets the mInstance
     */
    @Override public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    /**
     * Gets the one instance associated with this class
     */
    public static synchronized AppController getInstance() {
        return mInstance;
    }

    /**
     * Retrieves the request queue
     * @return The current RequestQueue
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    /**
     * Adds a request to the request queue with a custom tag
     * @param req A request
     * @param tag A tag for the request
     */
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    /**
     * Adds a request to the request queue with the default tag
     * @param req A request
     */
    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    /**
     * Cancels a pending request that has a certain tag
     * @param tag The tag of the request
     */
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}
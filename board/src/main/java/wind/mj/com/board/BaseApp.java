package wind.mj.com.board;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by wind on 9/11/15.
 */
public class BaseApp extends Application {
    private static RequestQueue requestQueue = null;
    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(this.getBaseContext());
    }

    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }
}

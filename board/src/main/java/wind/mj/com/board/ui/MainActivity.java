package wind.mj.com.board.ui;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import wind.mj.com.board.BaseApp;
import wind.mj.com.board.Config;
import wind.mj.com.board.R;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = MainActivity.class.getSimpleName();
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                ,WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏

        mListView = (ListView) findViewById(R.id.list);
        mListView.setAdapter(new ProductAdapter(this));

    }

    private void getProductionLineInfo(final String name,final String password) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_CURRENT_PRODUCTION_LINE_INFO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("YES")) {

                        } else  {

                        }
                    }
                }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG,volleyError.getMessage(),volleyError);
                if (Config.isDebug) {
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("login", name);
                map.put("password", password);
                map.put("flag",Config.STR_ZERO);
                return map;
            }
        };
        stringRequest.setTag(TAG);
        BaseApp.getRequestQueue().add(stringRequest);
    }

    private void getProductionLineListInfo(final String name,final String password) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_CURRENT_PRODUCTION_LINE_INFO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("YES")) {

                        } else  {

                        }
                    }
                }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG,volleyError.getMessage(),volleyError);
                if (Config.isDebug) {
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("login", name);
                map.put("password", password);
                map.put("flag",Config.STR_ONE);
                return map;
            }
        };
        stringRequest.setTag(TAG);
        BaseApp.getRequestQueue().add(stringRequest);
    }
}
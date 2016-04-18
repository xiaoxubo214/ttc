package wind.mj.com.ttc.activity.board;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mj.core.ui.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.greenrobot.event.EventBus;
import wind.mj.com.ttc.BaseApp;
import wind.mj.com.ttc.Config;
import wind.mj.com.ttc.R;
import wind.mj.com.ttc.event.MessageEvent;


public class HeadBoardActivity extends BaseActivity implements Runnable {
    private final static String TAG = HeadBoardActivity.class.getSimpleName();
    private Handler mHandler;
    private TextView mTimeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_head_board);
        ((TextView)findViewById(R.id.title)).setText(getString(R.string.end_board_title));
        mTimeView = (TextView) findViewById(R.id.time);
        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                mTimeView.setText((String)msg.obj);
            }
        };
        new Thread(this).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(MessageEvent event) {
        if  (event.getActionType() == MessageEvent.ACTION_GET_END_BOARD_DATA) {
            Log.e(TAG,"ACTION GET END BOARD DATA");
            getData("","");
        }
    }

    private void getData(final String name,final String password) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_END_BOARD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("YES")) {


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
                //map.put("login", name);
                //map.put("password", password);
                map.put("product_id","");
                return map;
            }
        };
        stringRequest.setTag(TAG);
        BaseApp.getRequestQueue().add(stringRequest);
    }

    @Override
    public void run() {
        try {
            while(true){
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日  HH:mm");
                String str=sdf.format(new Date());
                mHandler.sendMessage(mHandler.obtainMessage(100,str));
                EventBus.getDefault().post(new MessageEvent(MessageEvent.ACTION_GET_END_BOARD_DATA));
                Thread.sleep(30000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

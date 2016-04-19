package wind.mj.com.ttc.activity.board;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mj.core.ui.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;
import wind.mj.com.ttc.BaseApp;
import wind.mj.com.ttc.Config;
import wind.mj.com.ttc.R;
import wind.mj.com.ttc.adapter.HeadBoardDownAdapter;
import wind.mj.com.ttc.adapter.HeadBoardUpAdapter;
import wind.mj.com.ttc.event.MessageEvent;
import wind.mj.com.ttc.model.Error;
import wind.mj.com.ttc.model.HeadBoardDown;
import wind.mj.com.ttc.model.HeadBoardUp;
import wind.mj.com.ttc.utils.DataUtil;


public class HeadBoardActivity extends BaseActivity implements Runnable {
    private final static String TAG = HeadBoardActivity.class.getSimpleName();
    private Handler mHandler;
    private TextView mTimeView;
    private ListView mListView1,mListView2;
    private HeadBoardDownAdapter mHeadBoardDownAdapter;
    private HeadBoardUpAdapter mHeadBoardUpAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_head_board);
        ((TextView)findViewById(R.id.title)).setText(getString(R.string.head_board_title));
        mListView1 = (ListView) findViewById(R.id.id_listview1);
        mListView2 = (ListView) findViewById(R.id.id_listview2);
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
            getDataUp("","");
            getDataDown("","");
        }
    }

    private void getDataUp(final String name,final String password) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_HEAD_BOARD_UP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG,response.toString());
                        if (!response.contains("error")) {
                            List<HeadBoardUp> list = DataUtil.getHeadBoardUp(mContext,"",response.toString());
                            if (mHeadBoardUpAdapter == null) {
                                mHeadBoardUpAdapter = new HeadBoardUpAdapter(mContext,list);
                                mListView1.setAdapter(mHeadBoardUpAdapter);
                            } else {
                                mListView1.setAdapter(mHeadBoardUpAdapter);
                            }


                        } else {
                            Error error = DataUtil.getError(mContext,"",response.toString());
                            Toast.makeText(mContext,error.error,Toast.LENGTH_SHORT).show();
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
                map.put("database",Config.DATABASE);
                map.put("login", Config.DEFAULT_USERNAME);
                map.put("password", Config.DEFAULT_PASSWORD);
                map.put("line",Config.LINE_NUMBER);
                return map;
            }
        };
        stringRequest.setTag(TAG);
        BaseApp.getRequestQueue().add(stringRequest);
    }

    private void getDataDown(final String name,final String password) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_HEAD_BOARD_DOWN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG,response.toString());
                        if (!response.contains("error")) {
                            List<HeadBoardDown> list = DataUtil.getHeadBoardDown(mContext,"",response.toString());
                            if (mHeadBoardDownAdapter == null) {
                                mHeadBoardDownAdapter = new HeadBoardDownAdapter(mContext,list);
                                mListView2.setAdapter(mHeadBoardDownAdapter);
                            } else {
                                mListView2.setAdapter(mHeadBoardDownAdapter);
                            }


                        }  else {
                            Error error = DataUtil.getError(mContext,"",response.toString());
                            Toast.makeText(mContext,error.error,Toast.LENGTH_SHORT).show();
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
                map.put("database",Config.DATABASE);
                map.put("login", Config.DEFAULT_USERNAME);
                map.put("password", Config.DEFAULT_PASSWORD);
                map.put("line",Config.LINE_NUMBER);
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

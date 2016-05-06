package wind.mj.com.board.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mj.core.ui.BaseActivity;
import com.mj.core.util.SharedPrefsUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.greenrobot.event.EventBus;
import wind.mj.com.board.BaseApp;
import wind.mj.com.board.Config;
import wind.mj.com.board.R;
import wind.mj.com.board.event.MessageEvent;
import wind.mj.com.board.model.EndBoard;
import wind.mj.com.board.utils.DataUtil;
import wind.mj.com.board.model.Error;

public class EndBoardActivity extends BaseActivity implements Runnable {
    private final static String TAG = EndBoardActivity.class.getSimpleName();
    private Handler mHandler;
    private TextView mTimeView;

    private TextView mActualNumberView;
    private TextView mPlanNumberView;
    private TextView mStateView;
    private TextView mDifferenceView;
    private TextView mBadNumberView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_end_board);
        mActualNumberView = (TextView) findViewById(R.id.actual_number);
        mPlanNumberView = (TextView) findViewById(R.id.plan_number);
        mStateView = (TextView) findViewById(R.id.state);
        mDifferenceView = (TextView) findViewById(R.id.difference);
        mBadNumberView = (TextView) findViewById(R.id.bad_number);
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
                        if (!response.contains("error")) {
                            EndBoard endBoard = DataUtil.getEndBoard(mContext,"",response.toString());
                            mActualNumberView.setText(endBoard.actual_number);
                            mPlanNumberView.setText(endBoard.plan_number);
                            mStateView.setText(endBoard.state);
                            mDifferenceView.setText(endBoard.difference);

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
                    EndBoard endBoard = DataUtil.getEndBoard(mContext,"EndBoard.json","");
                    mActualNumberView.setText(endBoard.actual_number);
                    mPlanNumberView.setText(endBoard.plan_number);
                    mStateView.setText(endBoard.state);
                    mDifferenceView.setText(endBoard.difference);
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("database",Config.DATABASE);
                map.put("login", Config.DEFAULT_USERNAME);
                map.put("password", Config.DEFAULT_PASSWORD);
                map.put("line",getLineNumber());
                return map;
            }
        };
        stringRequest.setTag(TAG);
        BaseApp.getRequestQueue().add(stringRequest);
    }


    private String getLineNumber(){
        int number = SharedPrefsUtil.getInt(mContext,Config.KEY_WORKSTATION_NO);
        if (number == Config.LINE_ONE_START_BOARD || number == Config.LINE_ONE_END_BOARD) {
            return Config.LINE_NUMBER_1;
        } else if (number == Config.LINE_TWO_START_BOARD || number == Config.LINE_TWO_END_BOARD) {
            return Config.LINE_NUMBER_2;
        } else if (number == Config.LINE_THREE_START_BOARD || number == Config.LINE_THREE_END_BOARD) {
            return Config.LINE_NUMBER_3;
        } else if (number == Config.LINE_FOUR_START_BOARD || number == Config.LINE_FOUR_END_BOARD) {
            return Config.LINE_NUMBER_4;
        } else {
            return null;
        }
    }

    @Override
    public void run() {
        try {
            while(true){
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd  HH:mm");
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

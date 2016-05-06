package wind.mj.com.board.ui;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;
import wind.mj.com.board.BaseApp;
import wind.mj.com.board.Config;
import wind.mj.com.board.R;
import wind.mj.com.board.adapter.WarehouseBoardAdapter;
import wind.mj.com.board.event.MessageEvent;
import wind.mj.com.board.model.WareHouseBoard;
import wind.mj.com.board.utils.DataUtil;
import wind.mj.com.board.model.Error;

public class WarehouseBoardActivity extends BaseActivity implements Runnable {
    private final static String TAG = OfficeBoardActivity.class.getSimpleName();
    private Handler mHandler;
    private TextView mTimeView;
    private ListView mListView1,mListView2,mListView3,mListView4;
    private WarehouseBoardAdapter mWarehouseBoardAdapter1,mWarehouseBoardAdapter2;
    private WarehouseBoardAdapter mWarehouseBoardAdapter3,mWarehouseBoardAdapter4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_warehouse_board);
        mListView1 = (ListView) findViewById(R.id.id_listview1);
        mListView2 = (ListView) findViewById(R.id.id_listview2);
        mListView3 = (ListView) findViewById(R.id.id_listview3);
        mListView4 = (ListView) findViewById(R.id.id_listview4);
        ((TextView)findViewById(R.id.title)).setText(getString(R.string.warehouse_board_title));
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

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_WAREHOUSE_BOARD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG,response.toString());
                        if (!response.contains("error")) {
                            List<WareHouseBoard> wareHouseBoardList = DataUtil.getWarehouseBoard(mContext,"",response.toString());
                            List<WareHouseBoard> list1 = new ArrayList<>();
                            list1.clear();
                            List<WareHouseBoard> list2 = new ArrayList<>();
                            list2.clear();
                            List<WareHouseBoard> list3 = new ArrayList<>();
                            list3.clear();
                            List<WareHouseBoard> list4 = new ArrayList<>();
                            list4.clear();
                            if (wareHouseBoardList != null && wareHouseBoardList.size() != 0){
                                for (int i = 0;i< wareHouseBoardList.size();i++) {
                                    if (wareHouseBoardList.get(i).line.equals("00")){
                                        list1.add(wareHouseBoardList.get(i));
                                    } else if (wareHouseBoardList.get(i).line.equals("01")) {
                                        list2.add(wareHouseBoardList.get(i));
                                    } else if (wareHouseBoardList.get(i).line.equals("02")) {
                                        list3.add(wareHouseBoardList.get(i));
                                    } else if (wareHouseBoardList.get(i).line.equals("03")) {
                                        list4.add(wareHouseBoardList.get(i));
                                    }
                                }
                            }
                            if (list1.size() > 0) {
                                if (mWarehouseBoardAdapter1 == null) {
                                    mWarehouseBoardAdapter1 = new WarehouseBoardAdapter(mContext,list1);
                                    mListView1.setAdapter(mWarehouseBoardAdapter1);
                                } else {
                                    mListView1.setAdapter(mWarehouseBoardAdapter1);
                                }
                            }
                            if (list2.size() > 0) {
                                if (mWarehouseBoardAdapter2 == null) {
                                    mWarehouseBoardAdapter2 = new WarehouseBoardAdapter(mContext,list2);
                                    mListView2.setAdapter(mWarehouseBoardAdapter2);
                                } else {
                                    mListView2.setAdapter(mWarehouseBoardAdapter2);
                                }
                            }
                            if (list3.size() > 0) {
                                if (mWarehouseBoardAdapter3 == null) {
                                    mWarehouseBoardAdapter3 = new WarehouseBoardAdapter(mContext,list3);
                                    mListView3.setAdapter(mWarehouseBoardAdapter3);
                                } else {
                                    mListView3.setAdapter(mWarehouseBoardAdapter3);
                                }
                            }
                            if (list4.size() > 0) {
                                if (mWarehouseBoardAdapter4 == null) {
                                    mWarehouseBoardAdapter4 = new WarehouseBoardAdapter(mContext,list4);
                                    mListView4.setAdapter(mWarehouseBoardAdapter4);
                                } else {
                                    mListView4.setAdapter(mWarehouseBoardAdapter4);
                                }
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

                    List<WareHouseBoard> wareHouseBoardList = DataUtil.getWarehouseBoard(mContext,"WarehouseBoard.json","");
                    List<WareHouseBoard> list1 = new ArrayList<>();
                    list1.clear();
                    List<WareHouseBoard> list2 = new ArrayList<>();
                    list2.clear();
                    List<WareHouseBoard> list3 = new ArrayList<>();
                    list3.clear();
                    List<WareHouseBoard> list4 = new ArrayList<>();
                    list4.clear();
                    if (wareHouseBoardList != null && wareHouseBoardList.size() != 0){
                        for (int i = 0;i< wareHouseBoardList.size();i++) {
                            if (wareHouseBoardList.get(i).line.equals("00")){
                                list1.add(wareHouseBoardList.get(i));
                            } else if (wareHouseBoardList.get(i).line.equals("01")) {
                                list2.add(wareHouseBoardList.get(i));
                            } else if (wareHouseBoardList.get(i).line.equals("02")) {
                                list3.add(wareHouseBoardList.get(i));
                            } else if (wareHouseBoardList.get(i).line.equals("03")) {
                                list4.add(wareHouseBoardList.get(i));
                            }
                        }
                    }
                    if (list1.size() > 0) {
                        if (mWarehouseBoardAdapter1 == null) {
                            mWarehouseBoardAdapter1 = new WarehouseBoardAdapter(mContext,list1);
                            mListView1.setAdapter(mWarehouseBoardAdapter1);
                        } else {
                            mListView1.setAdapter(mWarehouseBoardAdapter1);
                        }
                    }
                    if (list2.size() > 0) {
                        if (mWarehouseBoardAdapter2 == null) {
                            mWarehouseBoardAdapter2 = new WarehouseBoardAdapter(mContext,list2);
                            mListView2.setAdapter(mWarehouseBoardAdapter2);
                        } else {
                            mListView2.setAdapter(mWarehouseBoardAdapter2);
                        }
                    }
                    if (list3.size() > 0) {
                        if (mWarehouseBoardAdapter3 == null) {
                            mWarehouseBoardAdapter3 = new WarehouseBoardAdapter(mContext,list3);
                            mListView3.setAdapter(mWarehouseBoardAdapter3);
                        } else {
                            mListView3.setAdapter(mWarehouseBoardAdapter3);
                        }
                    }
                    if (list4.size() > 0) {
                        if (mWarehouseBoardAdapter4 == null) {
                            mWarehouseBoardAdapter4 = new WarehouseBoardAdapter(mContext,list4);
                            mListView4.setAdapter(mWarehouseBoardAdapter4);
                        } else {
                            mListView4.setAdapter(mWarehouseBoardAdapter4);
                        }
                    }

                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("database",Config.DATABASE);
                map.put("login", Config.DEFAULT_USERNAME);
                map.put("password", Config.DEFAULT_PASSWORD);
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

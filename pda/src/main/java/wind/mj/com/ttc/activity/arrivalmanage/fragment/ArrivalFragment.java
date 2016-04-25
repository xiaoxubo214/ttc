package wind.mj.com.ttc.activity.arrivalmanage.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mj.core.ui.BaseFragment;
import com.mj.core.util.SharedPrefsUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;
import wind.mj.com.ttc.BaseApp;
import wind.mj.com.ttc.Config;
import wind.mj.com.ttc.R;
import wind.mj.com.ttc.adapter.ArrivalDetailAdapter;
import wind.mj.com.ttc.event.Event;
import wind.mj.com.ttc.event.MessageEvent;
import wind.mj.com.ttc.model.ArrivalDetail;
import wind.mj.com.ttc.utils.DataUtil;

public class ArrivalFragment extends BaseFragment {
    public final static String TAG = ArrivalFragment.class.getSimpleName();
    private ListView mListView;
    private ArrivalDetailAdapter mArrivalDetailAdapter;
    public ArrivalFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_arrival, container, false);
        mListView = (ListView)view.findViewById(R.id.id_listview);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //EventBus.getDefault().register(getActivity());
        getArrivalInfo(SharedPrefsUtil.getString(mContext, Config.STR_USERNAME),
                SharedPrefsUtil.getString(mContext, Config.STR_PASSWORD),
                SharedPrefsUtil.getString(mContext,Config.STR_CONTAINER_CODE,Config.STR_ZERO));
    }

    @Override
    public void onPause() {
        super.onPause();
        //EventBus.getDefault().unregister(getActivity());
    }

    /*public void onEventMainThread(MessageEvent event) {
        if  (event.getActionType() == MessageEvent.ACTION_REFRESH_ARRIVAL_INFO) {
            Log.e(TAG,"Action refresh arrival info");
            getArrivalInfo(SharedPrefsUtil.getString(mContext, Config.STR_USERNAME),
                    SharedPrefsUtil.getString(mContext, Config.STR_PASSWORD),
                    SharedPrefsUtil.getString(mContext,Config.STR_CONTAINER_CODE,Config.STR_ZERO));
        }
    }*/

    public void onEvent(MessageEvent event)
    {
        if  (event.getActionType() == MessageEvent.ACTION_REFRESH_ARRIVAL_INFO) {
            Log.e(TAG,"Action refresh arrival info");
            getArrivalInfo(SharedPrefsUtil.getString(mContext, Config.STR_USERNAME),
                    SharedPrefsUtil.getString(mContext, Config.STR_PASSWORD),
                    SharedPrefsUtil.getString(mContext,Config.STR_CONTAINER_CODE,Config.STR_ZERO));
        }
    }


    private void getArrivalInfo(final String name, final String password, final String code) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_ARRIVAL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.e(TAG,response.toString());
                        if (!response.contains("error")) {
                            List<ArrivalDetail> arrivalDetails = DataUtil.getArrivalDetail(mContext,"",response.toString());
                            if (mArrivalDetailAdapter == null) {
                                mArrivalDetailAdapter = new ArrivalDetailAdapter(mContext,arrivalDetails);
                                mListView.setAdapter(mArrivalDetailAdapter);

                            } else {
                                mListView.setAdapter(mArrivalDetailAdapter);
                            }

                        } else {
                            Toast.makeText(mContext,
                                    getString(R.string.get_fail),Toast.LENGTH_LONG).show();

                        }
                    }

                }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG,volleyError.getMessage(),volleyError);
                EventBus.getDefault().post(
                        new Event(Event.ACTION_ERROR,volleyError.getMessage()));
                //Toast.makeText(mContext,
                //        getString(R.string.get_fail),Toast.LENGTH_LONG).show();
                if (Config.isTest) {
                    List<ArrivalDetail> arrivalDetails = DataUtil.getArrivalDetail(mContext,"ArrivalDetail.json","");
                    if (mArrivalDetailAdapter == null) {
                        mArrivalDetailAdapter = new ArrivalDetailAdapter(mContext,arrivalDetails);
                        mListView.setAdapter(mArrivalDetailAdapter);

                    } else {
                        mListView.setAdapter(mArrivalDetailAdapter);
                    }
                }

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("login", name);
                map.put("password", password);
                map.put("code",code);
                return map;
            }
        };
        stringRequest.setTag(TAG);
        BaseApp.getRequestQueue().add(stringRequest);
    }

}

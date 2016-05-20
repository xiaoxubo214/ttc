package wind.mj.com.board.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wind.mj.com.board.BaseApp;
import wind.mj.com.board.Config;
import wind.mj.com.board.R;
import wind.mj.com.board.adapter.WarehouseBoardAdapter;
import wind.mj.com.board.model.Error;
import wind.mj.com.board.model.WareHouseBoard;
import wind.mj.com.board.utils.DataUtil;

public class OneFragment extends Fragment {
    private final String TAG = OneFragment.class.getSimpleName();
    private static final String ARG_PARAM1 = "index";
    private int mParam1;
    //private TextView mTitleView;
    private ListView mListView;
    private WarehouseBoardAdapter mWarehouseBoardAdapter;

    public OneFragment() {
        // Required empty public constructor
    }

    public static OneFragment newInstance(int index) {
        OneFragment fragment = new OneFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, index);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getData("","");
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        //mTitleView = (TextView) view.findViewById(R.id.title);
        //mTitleView.setText(R.string.product_name_1);
        mListView = (ListView) view.findViewById(R.id.id_listview1);
        return view;
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }

    private void getData(final String name,final String password) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_WAREHOUSE_BOARD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (!response.contains("error")) {
                            List<WareHouseBoard> wareHouseBoardList = DataUtil.getWarehouseBoard(getActivity(),"",response.toString());
                            List<WareHouseBoard> list = new ArrayList<>();
                            list.clear();
                            if (wareHouseBoardList != null && wareHouseBoardList.size() != 0){
                                for (int i = 0;i< wareHouseBoardList.size();i++) {
                                    if (wareHouseBoardList.get(i).line.equals(Config.LINE_NUMBER_1)){
                                        list.add(wareHouseBoardList.get(i));
                                    }
                                }
                            }
                            if (list.size() > 0) {
                                if (mWarehouseBoardAdapter == null) {
                                    mWarehouseBoardAdapter = new WarehouseBoardAdapter(getActivity(),list);
                                    mListView.setAdapter(mWarehouseBoardAdapter);
                                } else {
                                    mListView.setAdapter(mWarehouseBoardAdapter);
                                }
                            }
                        } else {
                            Error error = DataUtil.getError(getActivity(),"",response.toString());
                            Toast.makeText(getActivity(),error.error,Toast.LENGTH_SHORT).show();
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
                return map;
            }
        };
        stringRequest.setTag(TAG);
        BaseApp.getRequestQueue().add(stringRequest);
    }
}

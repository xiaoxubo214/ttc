package wind.mj.com.ttc.activity.warehouse;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mj.core.ui.BaseActivity;
import com.mj.core.util.SharedPrefsUtil;

import java.util.HashMap;
import java.util.Map;

import de.greenrobot.event.EventBus;
import wind.mj.com.ttc.BaseApp;
import wind.mj.com.ttc.Config;
import wind.mj.com.ttc.R;
import wind.mj.com.ttc.event.Event;
import wind.mj.com.ttc.model.ArrivalInbound;
import wind.mj.com.ttc.utils.DataUtil;

public class MaterialDrainActivity extends BaseActivity {
    public final static String TAG = MaterialDrainActivity.class.getSimpleName();
    EditText mScanInputView;
    EditText mNumberInputView;
    Button mSureView;

    TextView mCustomerModelView;//客户机型
    TextView mCodeView;//箱号
    TextView mWlzdCodeView;//物料编号
    TextView mBarCodeView;//条码
    TextView mWlzdNameEnView;//物料名称（英文）
    TextView mBatchView;//批次
    TextView mActualSumView;//数量
    TextView mWlzdNameView;//物料名称
    TextView mModelView;//用于机型
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.item_3);
        setContentView(R.layout.activity_material_drain);

        mScanInputView = (EditText) findViewById(R.id.scan_input);
        mNumberInputView = (EditText) findViewById(R.id.number_input);
        mSureView = (Button) findViewById(R.id.sure);
        mSureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "0";
                if (!mNumberInputView.getText().toString().isEmpty()) {
                    number = mNumberInputView.getText().toString();
                }
                if ((!mScanInputView.getText().toString().isEmpty())) {
                    checkArrivalInfo(SharedPrefsUtil.getString(mContext, Config.STR_USERNAME),
                            SharedPrefsUtil.getString(mContext, Config.STR_PASSWORD),
                            mScanInputView.getText().toString(),
                            number
                    );
                } else {
                    Toast.makeText(mContext,getString(R.string.barcode_is_empty),Toast.LENGTH_SHORT).show();
                }

            }
        });

        mCustomerModelView = (TextView) findViewById(R.id.customer_model);
        mCodeView = (TextView) findViewById(R.id.code);
        mWlzdCodeView = (TextView) findViewById(R.id.wlzd_code);
        mBarCodeView = (TextView) findViewById(R.id.bar_code);
        mWlzdNameEnView =(TextView) findViewById(R.id.wlzd_name_EN);
        mBatchView = (TextView) findViewById(R.id.batch) ;
        mActualSumView = (TextView) findViewById(R.id.actual_sum);
        mWlzdNameView = (TextView) findViewById(R.id.wlzd_name);
        mModelView = (TextView) findViewById(R.id.model);

    }

    private void checkArrivalInfo(final String name, final String password, final String barcode, final String number) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_PRODUCT_MATERIAL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG,response.toString());
                        if (!response.contains("error")) {
                            Toast.makeText(MaterialDrainActivity.this,
                                    getString(R.string.get_success),Toast.LENGTH_LONG).show();

                            ArrivalInbound arrivalInbound = DataUtil.getArrivalInbound(mContext,"",response.toString());
                            if (arrivalInbound != null) {
                                mCustomerModelView.setText(arrivalInbound.customer_model);
                                mCodeView.setText(arrivalInbound.code);
                                mWlzdCodeView.setText(arrivalInbound.wlzd_code);
                                mBarCodeView.setText(arrivalInbound.bar_code);
                                mWlzdNameEnView.setText(arrivalInbound.wlzd_name_EN);
                                mBatchView.setText(arrivalInbound.batch);
                                mActualSumView.setText(arrivalInbound.actual_sum);
                                mWlzdNameView.setText(arrivalInbound.wlzd_name);
                                mModelView.setText(arrivalInbound.model);
                            }

                        } else {
                            Toast.makeText(MaterialDrainActivity.this,
                                    getString(R.string.get_fail),Toast.LENGTH_LONG).show();

                        }
                    }

                }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG,volleyError.getMessage(),volleyError);
                EventBus.getDefault().post(
                        new Event(Event.ACTION_ERROR,volleyError.getMessage()));
                Toast.makeText(MaterialDrainActivity.this,
                        getString(R.string.get_fail),Toast.LENGTH_LONG).show();
                if (Config.isTest) {
                    ArrivalInbound arrivalInbound = DataUtil.getArrivalInbound(mContext,"ArrivalInbound.json","");
                    if (arrivalInbound != null) {
                        mCustomerModelView.setText(arrivalInbound.customer_model);
                        mCodeView.setText(arrivalInbound.code);
                        mWlzdCodeView.setText(arrivalInbound.wlzd_code);
                        mBarCodeView.setText(arrivalInbound.bar_code);
                        mWlzdNameEnView.setText(arrivalInbound.wlzd_name_EN);
                        mBatchView.setText(arrivalInbound.batch);
                        mActualSumView.setText(arrivalInbound.actual_sum);
                        mWlzdNameView.setText(arrivalInbound.wlzd_name);
                        mModelView.setText(arrivalInbound.model);
                    }

                }

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("database",Config.DATABASE);
                map.put("login", name);
                map.put("password", password);
                map.put("bar_code",barcode);
                map.put("number",number);
                return map;
            }
        };
        stringRequest.setTag(TAG);
        BaseApp.getRequestQueue().add(stringRequest);
    }

    private void playSound() {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play();
    }

}

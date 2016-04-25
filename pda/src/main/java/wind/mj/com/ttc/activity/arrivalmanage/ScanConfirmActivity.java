package wind.mj.com.ttc.activity.arrivalmanage;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.android.volley.Request.Method;
import com.android.volley.toolbox.StringRequest;
import com.mj.core.ui.BaseActivity;
import com.mj.core.util.SharedPrefsUtil;

import java.util.HashMap;
import java.util.Map;

import wind.mj.com.ttc.BaseApp;
import wind.mj.com.ttc.Config;
import wind.mj.com.ttc.R;
import wind.mj.com.ttc.model.Error;
import wind.mj.com.ttc.model.ScanConfirm;
import wind.mj.com.ttc.utils.DataUtil;


public class ScanConfirmActivity extends BaseActivity {
    public final static String TAG = ScanConfirmActivity.class.getSimpleName();
    EditText mScanInputView;
    Button mSureView;

    TextView mCustomerModelView;//客户机型
    TextView mCodeView;//箱号
    TextView mWlzdCodeView;//物料编号
    TextView mBarCodeView;//条码
    TextView mSpecificationView;//规格型号
    TextView mBatchView;//批次
    TextView mActualSumView;//数量
    TextView mWlzdNameView;//物料名称
    TextView mModelView;//用于机型
    TextView mDateArriveView;//到货日期
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.item_1);
        setContentView(R.layout.activity_scan_confirm);

        mScanInputView = (EditText) findViewById(R.id.scan_input);
        //mScanInputView.addTextChangedListener(textWatcher);
        mSureView = (Button) findViewById(R.id.sure);
        //mSureView.setVisibility(View.GONE);
        mSureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String number = "0";
                if (!mNumberInputView.getText().toString().isEmpty()) {
                    number = mNumberInputView.getText().toString();
                }*/
                if ((!mScanInputView.getText().toString().isEmpty())) {
                    checkArrivalInfo(SharedPrefsUtil.getString(mContext, Config.STR_USERNAME),
                            SharedPrefsUtil.getString(mContext, Config.STR_PASSWORD),
                            mScanInputView.getText().toString()
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
        mSpecificationView = (TextView) findViewById(R.id.specification);
        mBatchView = (TextView) findViewById(R.id.batch) ;
        mActualSumView = (TextView) findViewById(R.id.actual_sum);
        mWlzdNameView = (TextView) findViewById(R.id.wlzd_name);
        mModelView = (TextView) findViewById(R.id.model);
        mDateArriveView = (TextView) findViewById(R.id.date_arrive);

    }

    private void checkArrivalInfo(final String name, final String password, final String barcode) {
        StringRequest stringRequest = new StringRequest(Method.POST, Config.URL_SCAN_CONFIRM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG,"success" + response.toString());
                        if (!response.contains("error")) {
                            Log.e(TAG," " + (DataUtil.getScanConfirm(mContext,"",response.toString()) == null));
                            ScanConfirm scanConfirm = DataUtil.getScanConfirm(mContext, "", response.toString());
                            if (scanConfirm != null) {
                                mScanInputView.setText("");
                                playSound();

                                mCustomerModelView.setText(scanConfirm.customer_model);
                                mCodeView.setText(scanConfirm.code);
                                mWlzdCodeView.setText(scanConfirm.wlzd_code);
                                mBarCodeView.setText(scanConfirm.bar_code);
                                mSpecificationView.setText(scanConfirm.specification);
                                mBatchView.setText(scanConfirm.batch);
                                mActualSumView.setText(scanConfirm.actual_sum);
                                mWlzdNameView.setText(scanConfirm.wlzd_name);
                                mModelView.setText(scanConfirm.model);
                                mDateArriveView.setText(scanConfirm.date_arrive);
                            }
                        } else {
                            Log.e(TAG,"is null");
                            Error error = DataUtil.getError(mContext,"",response.toString());
                            Toast.makeText(mContext,error.error,Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG,"error");
                Log.e(TAG,volleyError.getMessage(),volleyError);
                if (Config.isDebug) {
                    ScanConfirm scanConfirm = DataUtil.getScanConfirm(mContext, "ScanConfirm.json", "");
                    if (scanConfirm != null) {
                        mCustomerModelView.setText(scanConfirm.customer_model);
                        mCodeView.setText(scanConfirm.code);
                        mWlzdCodeView.setText(scanConfirm.wlzd_code);
                        mBarCodeView.setText(scanConfirm.bar_code);
                        mSpecificationView.setText(scanConfirm.specification);
                        mBatchView.setText(scanConfirm.batch);
                        mActualSumView.setText(scanConfirm.actual_sum);
                        mWlzdNameView.setText(scanConfirm.wlzd_name);
                        mModelView.setText(scanConfirm.model);
                        mDateArriveView.setText(scanConfirm.date_arrive);
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("database",Config.DATABASE);
                map.put("login", name);
                map.put("password", password);
                map.put("bar_code",barcode);
                return map;
            }
        };
        stringRequest.setTag(TAG);
        BaseApp.getRequestQueue().add(stringRequest);

    }

    private TextWatcher textWatcher = new TextWatcher() {
        int length = 0;
        CharSequence value = "";
        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            length = s.toString().length();
            Log.e(TAG,"before length:  " + length);
        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.e(TAG,"char: " + s.toString());
            if (Math.abs((s.toString().length() - length)) > 1) {
                if ((!s.toString().isEmpty())) {
                    checkArrivalInfo(SharedPrefsUtil.getString(mContext, Config.STR_USERNAME),
                            SharedPrefsUtil.getString(mContext, Config.STR_PASSWORD),
                            s.toString()
                    );

                    if ((!value.toString().isEmpty()) && (!s.toString().equals(value)) &&
                            s.toString().contains(value)) {
                        //Log.e(TAG,"is contain");
                        Log.e(TAG,"value2: " + value);
                        mScanInputView.setText(value.toString());
                    } else {
                        value = s.toString();
                        Log.e(TAG,"value:  " + value);
                    }
                } else {
                    Toast.makeText(mContext,getString(R.string.barcode_is_empty),Toast.LENGTH_SHORT).show();
                }

            } else {
            }
        }



    };

    private void playSound() {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play();
    }

}

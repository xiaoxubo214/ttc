package wind.mj.com.ttc.activity.product;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import wind.mj.com.ttc.model.ProductOnline;
import wind.mj.com.ttc.utils.DataUtil;


public class ProductDrainActivity extends BaseActivity {
    private final static String TAG = ProductDrainActivity.class.getSimpleName();
    private TextView mInputLineView;
    private TextView mInputProductView;
    private TextView mInputMaterialView;
    private Button mSureView;

    private TextView mModelView;
    private TextView mCodeView;
    private TextView mNameView;
    private TextView mNumberView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle(R.string.item_10);
        setContentView(R.layout.activity_product_drain);
        mInputLineView = (TextView) findViewById(R.id.input_line);
        mInputProductView= (TextView) findViewById(R.id.input_product);
        mInputMaterialView = (TextView) findViewById(R.id.input_material);
        mSureView = (Button) findViewById(R.id.sure);
        mSureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!mInputLineView.getText().toString().isEmpty())
                        && (!mInputProductView.getText().toString().isEmpty()
                        &&(!mInputMaterialView.getText().toString().isEmpty()))) {
                    sendInfo(mInputLineView.getText().toString(),
                            mInputProductView.getText().toString(),
                            mInputMaterialView.getText().toString());
                }
            }
        });

        mModelView = (TextView) findViewById(R.id.model);
        mCodeView = (TextView) findViewById(R.id.code);
        mNameView = (TextView) findViewById(R.id.name);
        mNumberView = (TextView) findViewById(R.id.number);

    }

    public void sendInfo(String line,String product,String material){
        sendBarcode(SharedPrefsUtil.getString(this, Config.STR_USERNAME),
                SharedPrefsUtil.getString(this, Config.STR_PASSWORD), line,product,material);
    }



    private void sendBarcode(final String name, final String password, final String line,final String product,final String material) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_PRODUCT_DRAIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.e(TAG,response.toString());
                        if (response.contains("YES")) {
                            ProductOnline productOnline = DataUtil.getProductOnline(mContext,"",response.toString());
                            if (productOnline != null) {
                                mModelView.setText(productOnline.model);
                                mCodeView.setText(productOnline.code);
                                mNameView.setText(productOnline.name);
                                mNumberView.setText(productOnline.number);
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
                if (Config.isTest) {
                    ProductOnline productOnline = DataUtil.getProductOnline(mContext,"ProductOnline.json","");
                    if (productOnline != null) {
                        mModelView.setText(productOnline.model);
                        mCodeView.setText(productOnline.code);
                        mNameView.setText(productOnline.name);
                        mNumberView.setText(productOnline.number);
                    }
                }

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("login", name);
                map.put("password", password);
                map.put("line",line);
                map.put("wlzd_cp",product);
                map.put("wlzd_xh",material);
                return map;
            }
        };
        stringRequest.setTag(TAG);
        BaseApp.getRequestQueue().add(stringRequest);
    }
}

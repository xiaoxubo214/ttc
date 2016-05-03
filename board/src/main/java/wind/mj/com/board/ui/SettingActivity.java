package wind.mj.com.board.ui;

import android.content.Intent;
import android.net.ParseException;
import android.provider.Settings;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mj.core.ui.BaseActivity;
import com.mj.core.util.SharedPrefsUtil;

import wind.mj.com.board.Config;
import wind.mj.com.board.R;

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    public final static String TAG = SettingActivity.class.getSimpleName();

    Button mBtnSystemSetting;
    Button mBtnSave;
    Button mBtnReturn;
    EditText mEtWorkstationNo;
    EditText mEtServerIp;

    private int mWorkstationNo;
    private String mWorkstationNoStr;
    private String mServerIp;
    private String mFrom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                ,WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.activity_setting);
        if (null != getIntent() && null != getIntent().getExtras() && getIntent().getExtras().containsKey("from"))
            mFrom = getIntent().getExtras().getString("from");

        mEtServerIp = (EditText) findViewById(R.id.et_server_ip);
        mEtWorkstationNo = (EditText) findViewById(R.id.et_workstation_no);
        mEtWorkstationNo.setHint("min is " + Config.STATION_MIN + "  max is  " + Config.STATION_MAX);
        mBtnSystemSetting = (Button) findViewById(R.id.btn_system_setting);
        mBtnSystemSetting.setOnClickListener(this);
        mBtnSave = (Button) findViewById(R.id.btn_save);
        mBtnSave.setOnClickListener(this);
        mBtnReturn = (Button) findViewById(R.id.btn_return);
        mBtnReturn.setOnClickListener(this);

        mServerIp = SharedPrefsUtil.getString(this, Config.KEY_SERVER_IP);

        mEtServerIp.setText(TextUtils.isEmpty(mServerIp) ? mServerIp : Config.HOST);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_system_setting:
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                break;
            case R.id.btn_save:

                mServerIp = mEtServerIp.getText().toString().trim();
                mWorkstationNoStr = mEtWorkstationNo.getText().toString().trim();

                if (!mServerIp.equalsIgnoreCase("") && !mWorkstationNoStr.equalsIgnoreCase("")
                        ) {
                    try {
                        mWorkstationNo = Integer.parseInt(mWorkstationNoStr.trim());
                    } catch (ParseException ex) {
                        Toast.makeText(mContext, R.string.upper_set_tip, Toast.LENGTH_LONG).show();
                    }
                    SharedPrefsUtil.setValue(mContext, Config.KEY_SERVER_IP, mServerIp);
                    SharedPrefsUtil.setValue(mContext, Config.KEY_WORKSTATION_NO, mWorkstationNo);
                    SharedPrefsUtil.setValue(mContext, Config.KEY_IS_FIRST_RUN, Config.isStartRun);

                    finish();
                } else {
                    Toast.makeText(mContext, R.string.upper_set_tip, Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_return:
                if (!TextUtils.isEmpty(mFrom) && mFrom.equals("main")) {
                    Toast.makeText(mContext, R.string.first_start_message, Toast.LENGTH_LONG).show();
                } else {
                    finish();
                }
                break;
        }
    }

}
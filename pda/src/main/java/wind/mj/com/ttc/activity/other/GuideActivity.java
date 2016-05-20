package wind.mj.com.ttc.activity.other;

import android.location.SettingInjectorService;
import android.os.Bundle;
import android.util.Log;

import com.mj.core.ui.BaseActivity;
import com.mj.core.util.IntentUtils;
import com.mj.core.util.SharedPrefsUtil;

import wind.mj.com.ttc.Config;
import wind.mj.com.ttc.R;


public class GuideActivity extends BaseActivity {
    public final static String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean isFirstOpen = !SharedPrefsUtil.contains(mContext, Config.KEY_IS_FIRST_RUN);
        if (isFirstOpen) {
            Bundle bundle = new Bundle();
            bundle.putString("from", "main");
            IntentUtils.forward(mContext, InputSettingActivity.class, bundle);
        } else {
            IntentUtils.forward(mContext,LoginActivity.class);
            finish();
        }
    }
}

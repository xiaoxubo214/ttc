package wind.mj.com.board.ui;

import android.os.Bundle;
import android.util.Log;

import com.mj.core.ui.BaseActivity;
import com.mj.core.util.IntentUtils;
import com.mj.core.util.SharedPrefsUtil;

import wind.mj.com.board.Config;
import wind.mj.com.board.R;

public class MainActivity extends BaseActivity {
    public final static String TAG = MainActivity.class.getSimpleName();
    private int mWorkStation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean isFirstOpen = !SharedPrefsUtil.contains(mContext, Config.KEY_IS_FIRST_RUN);
        if (isFirstOpen) {
            Bundle bundle = new Bundle();
            bundle.putString("from", "main");
            IntentUtils.forward(mContext, SettingActivity.class, bundle);
        } else {
            mWorkStation = SharedPrefsUtil.getInt(this,
                    Config.KEY_WORKSTATION_NO);
            startToWorkStation();
        }
    }

    private void startToWorkStation() {
        if ((mWorkStation == Config.LINE_ONE_START_BOARD)
                || (mWorkStation == Config.LINE_TWO_START_BOARD)
                || (mWorkStation == Config.LINE_THREE_START_BOARD)
                || (mWorkStation == Config.LINE_FOUR_START_BOARD)) {

            IntentUtils.forward(mContext,HeadBoardActivity.class);

        } else if ((mWorkStation == Config.LINE_ONE_END_BOARD)
                || (mWorkStation == Config.LINE_TWO_END_BOARD)
                || (mWorkStation == Config.LINE_THREE_END_BOARD)
                || (mWorkStation == Config.LINE_FOUR_END_BOARD)) {

            IntentUtils.forward(mContext,EndBoardActivity.class);

        } else if (mWorkStation == Config.OFFICE_BOARD) {

            IntentUtils.forward(mContext, OfficeBoardActivity.class);

        } else if (mWorkStation == Config.WAREHOUSE_BOARD) {

            IntentUtils.forward(mContext,WarehouseTab2Activity.class);

        } else {
            Log.e(TAG,"Error");
        }
    }
}

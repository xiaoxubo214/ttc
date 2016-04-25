package wind.mj.com.ttc.activity.arrivalmanage;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mj.core.util.SharedPrefsUtil;

import de.greenrobot.event.EventBus;
import wind.mj.com.ttc.Config;
import wind.mj.com.ttc.R;
import wind.mj.com.ttc.activity.arrivalmanage.fragment.ArrivalFragment;
import wind.mj.com.ttc.activity.arrivalmanage.fragment.NotArrivalFragment;
import wind.mj.com.ttc.event.MessageEvent;

public class ArrivalQueryActivity extends FragmentActivity {
    private final static String TAG = ArrivalQueryActivity.class.getSimpleName();
    private FragmentTabHost mTabHost = null;
    private LinearLayout mRefreshLayout = null;
    private TextView mInputCodeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrival_query_actvity);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.tabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("0").setIndicator(getString(R.string.arrival_detail)), ArrivalFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("1").setIndicator(getString(R.string.not_arrival_detail)), NotArrivalFragment.class, null);

        mRefreshLayout = (LinearLayout) findViewById(R.id.refresh);
        mRefreshLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent(MessageEvent.ACTION_REFRESH_ARRIVAL_INFO));
            }
        });
        mInputCodeView = (TextView) findViewById(R.id.input_code);
        mInputCodeView.setText(SharedPrefsUtil.getString(this,Config.STR_CONTAINER_CODE,getString(R.string.please_input_container_code)));
        mInputCodeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

    }

    private void showDialog() {
        final EditText containerCodeView = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.container_code)).setView(containerCodeView)
                .setNegativeButton(getString(R.string.cancel), null);
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                if (containerCodeView.getText() != null && (!containerCodeView.getText().toString().isEmpty())) {
                    SharedPrefsUtil.setValue(ArrivalQueryActivity.this, Config.STR_CONTAINER_CODE,
                            containerCodeView.getText().toString());
                    mInputCodeView.setText(containerCodeView.getText().toString());

                }
            }
        });
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //EventBus.getDefault().unregister(this);
    }

    /*public void onEventMainThread(MessageEvent event) {
        if (event.getActionType() == MessageEvent.ACTION_REFRESH_ARRIVAL_INFO) {
            Log.e(TAG,"Action refresh arrival info");
        }
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTabHost = null;
    }

}
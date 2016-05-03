package wind.mj.com.board.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.mj.core.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import wind.mj.com.board.R;
import wind.mj.com.board.event.Event;
import wind.mj.com.board.event.MessageEvent;
import wind.mj.com.board.ui.fragment.FourFragment;
import wind.mj.com.board.ui.fragment.OneFragment;
import wind.mj.com.board.ui.fragment.ThreeFragment;
import wind.mj.com.board.ui.fragment.TwoFragment;
import wind.mj.com.board.util.Utils;

public class TabActivity extends BaseActivity implements View.OnClickListener {

    public final static String TAG = "MainActivity";

    TableRow mLineLayout1;
    TextView mLineText1;
    TableRow mLineLayout2;
    TextView mLineText2;
    TableRow mLineLayout3;
    TextView mLineText3;
    TableRow mLineLayout4;
    TextView mLineText4;


    List<TableRow> layouts = new ArrayList<>();
    List<TextView> textViews = new ArrayList<>();

    private OneFragment mOneFragment = null;
    private TwoFragment mTwoFragment = null;
    private ThreeFragment mThreeFragment = null;
    private FourFragment mFourFragment = null;

    private int mCurrentIndex = 0;

    private Handler mDataRefreshHandler = new Handler();
    private long mDelayTime = 8 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        initView();
        initViewList();

        //默认显示产线信息
        if (mOneFragment == null || mOneFragment.getShownIndex() != 0) {
            mOneFragment = OneFragment.newInstance(0);
        }
        Utils.replaceFragment(this, mTwoFragment, R.id.details, false);

        //刷新任务列表
        mDataRefreshHandler.removeCallbacks(mDataRefreshRunnable);
        mDataRefreshHandler.postDelayed(mDataRefreshRunnable, mDelayTime);
    }

    private void initView() {
        mLineLayout1 = (TableRow) findViewById(R.id.line_layout_1);
        mLineLayout1.setOnClickListener(this);
        mLineLayout2 = (TableRow) findViewById(R.id.line_layout_2);
        mLineLayout2.setOnClickListener(this);
        mLineLayout3 = (TableRow) findViewById(R.id.line_layout_3);
        mLineLayout3.setOnClickListener(this);
        mLineLayout4 = (TableRow) findViewById(R.id.line_layout_4);
        mLineLayout4.setOnClickListener(this);

        mLineText1 = (TextView) findViewById(R.id.line_info_1);
        mLineText2 = (TextView) findViewById(R.id.line_info_2);
        mLineText3 = (TextView) findViewById(R.id.line_info_3);
        mLineText4 = (TextView) findViewById(R.id.line_info_4);
    }

    private void initViewList() {
        layouts.clear();
        layouts.add(mLineLayout1);
        layouts.add(mLineLayout2);
        layouts.add(mLineLayout3);
        layouts.add(mLineLayout4);

        textViews.clear();
        textViews.add(mLineText1);
        textViews.add(mLineText2);
        textViews.add(mLineText3);
        textViews.add(mLineText4);
    }

    @Override
    public void onClick(View v) {
        int index = 0;
        switch (v.getId()) {
            case R.id.line_layout_1:
                index = 0;
                if (mCurrentIndex != index)
                    replaceOneFragment(index);

                mCurrentIndex = index;
                mDataRefreshHandler.removeCallbacks(mDataRefreshRunnable);
                mDataRefreshHandler.postDelayed(mDataRefreshRunnable, mDelayTime);
                break;
            case R.id.line_layout_2:
                index = 1;
                if (mCurrentIndex != index)
                   replaceTwoFragment(index);

                mCurrentIndex = index;
                mDataRefreshHandler.removeCallbacks(mDataRefreshRunnable);
                mDataRefreshHandler.postDelayed(mDataRefreshRunnable, mDelayTime);
                break;
            case R.id.line_layout_3:
                index = 2;
                if (mCurrentIndex != index)
                    replaceThreeFragment(index);

                mCurrentIndex = index;
                mDataRefreshHandler.removeCallbacks(mDataRefreshRunnable);
                mDataRefreshHandler.postDelayed(mDataRefreshRunnable, mDelayTime);
                break;
            case R.id.line_layout_4:
                index = 3;
                if (mCurrentIndex != index)
                    replaceFourFragment(index);

                mCurrentIndex = index;
                mDataRefreshHandler.removeCallbacks(mDataRefreshRunnable);
                mDataRefreshHandler.postDelayed(mDataRefreshRunnable, mDelayTime);
                break;
        }
    }

    public void Circulating() {
        Log.e(TAG,"recycle");
        if (mCurrentIndex > 3) {
            mCurrentIndex = 0;
        }
        switch (mCurrentIndex) {
            case 0:
                replaceOneFragment(mCurrentIndex);
                break;
            case 1:
                replaceTwoFragment(mCurrentIndex);
                break;
            case 2:
                replaceThreeFragment(mCurrentIndex);
                break;
            case 3:
                replaceFourFragment(mCurrentIndex);
                break;
        }
    }

    //-----replace-start-------
    private void replaceOneFragment(int index) {
        refreshColor(index);
        if (mOneFragment == null || mOneFragment.getShownIndex() != index) {
            mOneFragment = OneFragment.newInstance(index);
        }
        Utils.replaceFragment(this, mOneFragment, R.id.details, false);
    }

    private void replaceTwoFragment(int index) {
        refreshColor(index);
        if (mTwoFragment == null || mTwoFragment.getShownIndex() != index) {
            mTwoFragment = TwoFragment.newInstance(index);
        }
        Utils.replaceFragment(this, mTwoFragment, R.id.details, false);
    }

    private void replaceThreeFragment(int index) {
        refreshColor(index);
        if (mThreeFragment == null || mThreeFragment.getShownIndex() != index) {
            mThreeFragment = ThreeFragment.newInstance(index);
        }
        Utils.replaceFragment(this, mThreeFragment, R.id.details, false);
    }

    private void replaceFourFragment(int index) {
        refreshColor(index);
        if (mFourFragment == null || mFourFragment.getShownIndex() != index) {
            mFourFragment = FourFragment.newInstance(index);
        }
        Utils.replaceFragment(this, mFourFragment, R.id.details, false);
    }

    //-----replace-end-------

    private Runnable mDataRefreshRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                mCurrentIndex++;
                Circulating();
            } catch (Exception e) {
                e.printStackTrace();
                EventBus.getDefault().post(new Event(e.getMessage(),Event.ACTION_ERROR));
            }
            mDataRefreshHandler.postDelayed(mDataRefreshRunnable, mDelayTime);
        }
    };

    private void refreshColor(int index) {
        for (int i = 0; i < layouts.size(); i++) {
            if (i == index) {
                layouts.get(i).setBackgroundColor(getResources().getColor(R.color.nav_list_press));
                textViews.get(i).setTextColor(Color.WHITE);
            } else {
                layouts.get(i).setBackgroundColor(getResources().getColor(R.color.nav_list_bg));
                textViews.get(i).setTextColor(getResources().getColor(R.color.nav_text_color));
            }
        }
    }


    @Override
    protected void onResume(){
        super.onResume();
        EventBus.getDefault().register(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(MessageEvent event) {
        if (event.getActionType() == Event.ACTION_ERROR) {
            Toast.makeText(this, event.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}

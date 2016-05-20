package wind.mj.com.board.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Toast;

import de.greenrobot.event.EventBus;
import wind.mj.com.board.R;
import wind.mj.com.board.event.Event;
import wind.mj.com.board.event.MessageEvent;
import wind.mj.com.board.ui.fragment.FourFragment;
import wind.mj.com.board.ui.fragment.OneFragment;
import wind.mj.com.board.ui.fragment.ThreeFragment;
import wind.mj.com.board.ui.fragment.TwoFragment;
import wind.mj.com.board.view.PagerSlidingTabStrip;

public class WarehouseTab2Activity extends FragmentActivity implements Runnable {

    public final static String TAG = "MainActivity";

    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private MyPagerAdapter adapter;

    int currentIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse_tab2);

        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new MyPagerAdapter(getSupportFragmentManager());

        pager.setAdapter(adapter);

        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        pager.setPageMargin(pageMargin);

        tabs.setViewPager(pager);

        new Thread(this).start();
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = {
                getResources().getString(R.string.product_name_1),
                getResources().getString(R.string.product_name_2),
                getResources().getString(R.string.product_name_3),
                getResources().getString(R.string.product_name_4)
        };

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            Log.e(TAG,"click is:"  + position);
            if (position == 0) {
                return  OneFragment.newInstance(0);
            } else if (position == 1) {
                return  TwoFragment.newInstance(0);
            } else if (position == 2) {
                return  ThreeFragment.newInstance(0);
            } else if (position == 3) {
                return  FourFragment.newInstance(0);
            } else {
                return null;
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
        } if (event.getActionType() == MessageEvent.ACTION_CHANGE_FRAGMENT) {
            if (currentIndex == 0 || currentIndex == 1 || currentIndex == 2) {
                currentIndex++;
            } else if (currentIndex == 3) {
                currentIndex = 0;
            }
            pager.setCurrentItem(currentIndex);
        }
    }

    @Override
    public void run() {
        try {
            while(true){
                Thread.sleep(20000);
                EventBus.getDefault().post(new MessageEvent(MessageEvent.ACTION_CHANGE_FRAGMENT));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

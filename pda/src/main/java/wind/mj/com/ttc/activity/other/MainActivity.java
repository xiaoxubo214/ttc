package wind.mj.com.ttc.activity.other;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import com.mj.core.ui.BaseActivity;
import java.util.ArrayList;

import wind.mj.com.ttc.R;
import wind.mj.com.ttc.activity.arrivalmanage.ArrivalQueryActivity;
import wind.mj.com.ttc.activity.arrivalmanage.ScanConfirmActivity;
import wind.mj.com.ttc.activity.product.ProductFinishActivity;
import wind.mj.com.ttc.activity.product.ProductOnLineActivity;
import wind.mj.com.ttc.activity.warehouse.HeadScanActivity;
import wind.mj.com.ttc.activity.warehouse.PackScanActivity;
import wind.mj.com.ttc.activity.product.ProductDrainActivity;
import wind.mj.com.ttc.activity.warehouse.MaterialDrainActivity;
import wind.mj.com.ttc.activity.warehouse.ProductInboundActivity;
import wind.mj.com.ttc.activity.warehouse.ReturnWarehouseActivity;
import wind.mj.com.ttc.activity.warehouse.SaleActivity;
import wind.mj.com.ttc.adapter.GridAdapter;

public class MainActivity extends BaseActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    private GridView mGridView;
    private ArrayList<String> mNameList;
    private ArrayList<Drawable> mDrawableList;
    private ArrayList<Intent> mIntentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        setTitle("");
        mGridView = (GridView) findViewById(R.id.gridView);
        initData();
        mGridView.setAdapter(new GridAdapter(this,mNameList,mDrawableList));
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG,"position: " + position);
                startActivity(mIntentList.get(position));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this,SettingActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initData(){
        mNameList = new ArrayList<>();
        mDrawableList = new ArrayList<>();
        mIntentList = new ArrayList<>();



        mNameList.add(getString(R.string.item_1));
        mDrawableList.add(ContextCompat.getDrawable(this,R.drawable.ic_launcher));
        mIntentList.add(new Intent(this,ScanConfirmActivity.class));

        mNameList.add(getString(R.string.item_2));
        mDrawableList.add(ContextCompat.getDrawable(this,R.drawable.ic_launcher));
        mIntentList.add(new Intent(this,ArrivalQueryActivity.class));

        mNameList.add(getString(R.string.item_3));
        mDrawableList.add(ContextCompat.getDrawable(this,R.drawable.ic_launcher));
        mIntentList.add(new Intent(this,MaterialDrainActivity.class));

        mNameList.add(getString(R.string.item_4));
        mDrawableList.add(ContextCompat.getDrawable(this,R.drawable.ic_launcher));
        mIntentList.add(new Intent(this,HeadScanActivity.class));

        mNameList.add(getString(R.string.item_5));
        mDrawableList.add(ContextCompat.getDrawable(this,R.drawable.ic_launcher));
        mIntentList.add(new Intent(this,PackScanActivity.class));

        mNameList.add(getString(R.string.item_6));
        mDrawableList.add(ContextCompat.getDrawable(this,R.drawable.ic_launcher));
        mIntentList.add(new Intent(this,ReturnWarehouseActivity.class));

        mNameList.add(getString(R.string.item_7));
        mDrawableList.add(ContextCompat.getDrawable(this,R.drawable.ic_launcher));
        mIntentList.add(new Intent(this,ProductInboundActivity.class));

        mNameList.add(getString(R.string.item_8));
        mDrawableList.add(ContextCompat.getDrawable(this,R.drawable.ic_launcher));
        mIntentList.add(new Intent(this,SaleActivity.class));

        /*mNameList.add(getString(R.string.item_9));
        mDrawableList.add(ContextCompat.getDrawable(this,R.drawable.ic_launcher));
        mIntentList.add(new Intent(this,ProductOnLineActivity.class));

        mNameList.add(getString(R.string.item_10));
        mDrawableList.add(ContextCompat.getDrawable(this,R.drawable.ic_launcher));
        mIntentList.add(new Intent(this,ProductDrainActivity.class));

        mNameList.add(getString(R.string.item_11));
        mDrawableList.add(ContextCompat.getDrawable(this,R.drawable.ic_launcher));
        mIntentList.add(new Intent(this,ProductFinishActivity.class));*/

    }
}

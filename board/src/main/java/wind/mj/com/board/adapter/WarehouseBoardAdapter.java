package wind.mj.com.board.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import wind.mj.com.board.R;
import wind.mj.com.board.model.WareHouseBoard;

/**
 * Created by wind on 16/4/12.
 */
public class WarehouseBoardAdapter extends BaseAdapter {
    private List<WareHouseBoard> mList = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;
    LinearLayout.LayoutParams params;

    public WarehouseBoardAdapter(Context context, List<WareHouseBoard> list) {
        mList = list;
        mContext = context;
        mInflater = LayoutInflater.from(context);

        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
    }

    public int getCount() {
        return mList.size();
    }

    public Object getItem(int position) {
        return mList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewTag viewTag;

        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.item_warehouse_board, null);

            viewTag = new ItemViewTag(
                    (TextView) convertView.findViewById(R.id.customer),
                    (TextView) convertView.findViewById(R.id.batch),
                    (TextView) convertView.findViewById(R.id.online_number),
                    (TextView) convertView.findViewById(R.id.state),
                    (TextView) convertView.findViewById(R.id.wl01),
                    (TextView) convertView.findViewById(R.id.wl02),
                    (TextView) convertView.findViewById(R.id.wl03),
                    (TextView) convertView.findViewById(R.id.wl04),
                    (TextView) convertView.findViewById(R.id.time),
                    (TextView) convertView.findViewById(R.id.model),
                    (TextView) convertView.findViewById(R.id.plan_number),
                    (TextView) convertView.findViewById(R.id.line));
            convertView.setTag(viewTag);
        } else
        {
            viewTag = (ItemViewTag) convertView.getTag();
        }

        viewTag.customer.setText(mList.get(position).customer);
        viewTag.batch.setText(mList.get(position).batch);
        viewTag.online_number.setText(mList.get(position).online_number);
        viewTag.state.setText(mList.get(position).state);
        viewTag.wl01.setText(mList.get(position).wl01);
        viewTag.wl02.setText(mList.get(position).wl02);
        viewTag.wl03.setText(mList.get(position).wl03);
        viewTag.wl04.setText(mList.get(position).wl04);
        viewTag.time.setText(mList.get(position).time);
        viewTag.model.setText(mList.get(position).model);
        viewTag.plan_number.setText(mList.get(position).plan_number);
        viewTag.line.setText(mList.get(position).line);


        return convertView;
    }

    class ItemViewTag
    {
        public TextView customer;//": "123213",        #客户
        public TextView batch;//": "121222",		 #批次
        public TextView online_number;//": 0,          #在线数量
        public TextView state;//": "快",  		 #进度
        public TextView wl01;//": 0,			 #压缩机
        public TextView wl02;//": 0,			 #线号（00：OD1,01:ID1,02:OD2,03:ID2）一共四条线
        public TextView wl03;//": 0,			 #风扇
        public TextView wl04;//": 0,			 #控制器/电路器
        public TextView time;//": "10:00-11:00",       #时间段
        public TextView model;//": "GWC09ZC-K3NNA1A/I 顶(伊莱克斯专用)", #机型
        public TextView plan_number;//": 100,					#计划数量
        public TextView line;//": "01"


        public ItemViewTag(TextView customer,TextView batch,TextView online_number,
                           TextView state, TextView wl01,TextView wl02,TextView wl03,
                           TextView wl04,TextView time,TextView model,TextView plan_number,
                           TextView line)
        {
            this.customer = customer;
            this.batch = batch;
            this.online_number = online_number;
            this.state = state;
            this.wl01 = wl01;
            this.wl02 = wl02;
            this.wl03 = wl03;
            this.wl04 = wl04;
            this.time = time;
            this.model = model;
            this.plan_number = plan_number;
            this.line = line;

        }
    }

}

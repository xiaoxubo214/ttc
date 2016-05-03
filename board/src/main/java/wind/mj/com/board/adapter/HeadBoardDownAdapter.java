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
import wind.mj.com.board.model.HeadBoardDown;

/**
 * Created by wind on 16/4/12.
 */
public class HeadBoardDownAdapter extends BaseAdapter {
    private List<HeadBoardDown> mList = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;
    LinearLayout.LayoutParams params;

    public HeadBoardDownAdapter(Context context, List<HeadBoardDown> list) {
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
            convertView = mInflater.inflate(R.layout.item_head_board_down, null);

            viewTag = new ItemViewTag(
                    (TextView) convertView.findViewById(R.id.time),
                    (TextView) convertView.findViewById(R.id.model),
                    (TextView) convertView.findViewById(R.id.plan_number),
                    (TextView) convertView.findViewById(R.id.online_number),
                    (TextView) convertView.findViewById(R.id.wl01),
                    (TextView) convertView.findViewById(R.id.wl02),
                    (TextView) convertView.findViewById(R.id.wl03),
                    (TextView) convertView.findViewById(R.id.wl04));
            convertView.setTag(viewTag);
        } else
        {
            viewTag = (ItemViewTag) convertView.getTag();
        }

        viewTag.time.setText(mList.get(position).time);
        viewTag.model.setText(mList.get(position).model);
        viewTag.plan_number.setText(mList.get(position).plan_number);
        viewTag.online_number.setText(mList.get(position).online_number);
        viewTag.wl01.setText(mList.get(position).wl01);
        viewTag.wl02.setText(mList.get(position).wl02);
        viewTag.wl03.setText(mList.get(position).wl03);
        viewTag.wl04.setText(mList.get(position).wl04);

        if (position%2 == 1) {
            convertView.setBackground(mContext.getResources().getDrawable(R.drawable.white_gray));
        }

        return convertView;
    }

    class ItemViewTag
    {
        public TextView time;//": "01:00-02:00",
        public TextView model;//": "GWC09ZC-K3NNA1A/I 顶(伊莱克斯专用)",
        public TextView plan_number;//: 300,
        public TextView online_number;//": 300
        public TextView wl01;//": 0,
        public TextView wl02;//": 0,
        public TextView wl03;//": 0,
        public TextView wl04;//": 0,

        public ItemViewTag(TextView time, TextView model,TextView plan_number,
                           TextView online_number,TextView wl01,TextView wl02,
                           TextView wl03,TextView wl04)
        {
            this.time = time;
            this.model = model;
            this.plan_number = plan_number;
            this.online_number = online_number;
            this.wl01 = wl01;
            this.wl02 = wl02;
            this.wl03 = wl03;
            this.wl04 = wl04;

        }
    }

}

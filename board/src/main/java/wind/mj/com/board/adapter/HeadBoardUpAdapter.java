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
import wind.mj.com.board.model.HeadBoardUp;


/**
 * Created by wind on 16/4/12.
 */
public class HeadBoardUpAdapter extends BaseAdapter {
    private List<HeadBoardUp> mList = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;
    LinearLayout.LayoutParams params;

    public HeadBoardUpAdapter(Context context, List<HeadBoardUp> list) {
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
            convertView = mInflater.inflate(R.layout.item_head_board_up, null);

            viewTag = new ItemViewTag(
                    (TextView) convertView.findViewById(R.id.customer),
                    (TextView) convertView.findViewById(R.id.model),
                    (TextView) convertView.findViewById(R.id.plan_number));
            convertView.setTag(viewTag);
        } else
        {
            viewTag = (ItemViewTag) convertView.getTag();
        }

        viewTag.customer.setText(mList.get(position).customer);
        viewTag.model.setText(mList.get(position).model);
        viewTag.plan_number.setText(mList.get(position).plan_number);

        if (position%2 == 1) {
            convertView.setBackground(mContext.getResources().getDrawable(R.drawable.white_gray));
        }

        return convertView;
    }

    class ItemViewTag
    {
        public TextView customer;//": "埃里克森",
        public TextView model;//": "GWC09ZC-K3NNA1A/I 顶(伊莱克斯专用)",
        public TextView plan_number;//": 100
        public ItemViewTag(TextView customer,TextView model,TextView plan_number)
        {
            this.customer = customer;
            this.model = model;
            this.plan_number = plan_number;
        }
    }

}

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
import wind.mj.com.board.model.OfficeBoard;

/**
 * Created by wind on 16/4/12.
 */
public class OfficeBoardAdapter extends BaseAdapter {
    private List<OfficeBoard> mList = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;
    LinearLayout.LayoutParams params;

    public OfficeBoardAdapter(Context context, List<OfficeBoard> list) {
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
            convertView = mInflater.inflate(R.layout.item_office_board, null);

            viewTag = new ItemViewTag(
                    (TextView) convertView.findViewById(R.id.customer),
                    (TextView) convertView.findViewById(R.id.model),
                    (TextView) convertView.findViewById(R.id.state),
                    (TextView) convertView.findViewById(R.id.actual_number),
                    (TextView) convertView.findViewById(R.id.line),
                    (TextView) convertView.findViewById(R.id.plan_number),
                    (TextView) convertView.findViewById(R.id.online_number));
            convertView.setTag(viewTag);
        } else
        {
            viewTag = (ItemViewTag) convertView.getTag();
        }

        viewTag.customer.setText(mList.get(position).customer);
        viewTag.model.setText(mList.get(position).model);
        viewTag.state.setText(mList.get(position).state);
        viewTag.actual_number.setText(mList.get(position).actual_number);
        viewTag.line.setText(mList.get(position).line);
        viewTag.plan_number.setText(mList.get(position).plan_number);
        viewTag.online_number.setText(mList.get(position).online_number);

        if (position%2 == 1) {
            convertView.setBackground(mContext.getResources().getDrawable(R.drawable.white_gray));
        }

        return convertView;
    }

    class ItemViewTag
    {
        public TextView customer;//": false,
        public TextView model;//": false,
        public TextView state;//": false,
        public TextView actual_number;//": 300,
        public TextView line;//": "01",
        public TextView plan_number;//": 1000,
        public TextView online_number;//": 200
        public ItemViewTag(TextView customer,TextView model,TextView state,
                           TextView actual_number,TextView line,TextView plan_number,
                           TextView online_number)
        {
            this.customer = customer;
            this.model = model;
            this.state = state;
            this.actual_number = actual_number;
            this.line = line;
            this.plan_number = plan_number;
            this.online_number = online_number;

        }
    }

}

package wind.mj.com.ttc.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import wind.mj.com.ttc.R;
import wind.mj.com.ttc.model.ArrivalDetail;

/**
 * Created by wind on 16/4/12.
 */
public class ArrivalDetailAdapter extends BaseAdapter {
    private List<ArrivalDetail> mList = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;
    LinearLayout.LayoutParams params;

    public ArrivalDetailAdapter(Context context, List<ArrivalDetail> list) {
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
            convertView = mInflater.inflate(R.layout.item_arrival_detail, null);

            viewTag = new ItemViewTag((TextView) convertView.findViewById(R.id.customer_model),
                    (TextView) convertView.findViewById(R.id.code),
                    (TextView) convertView.findViewById(R.id.wlzd_code),
                    (TextView) convertView.findViewById(R.id.bar_code),
                    (TextView) convertView.findViewById(R.id.wlzd_name),
                    (TextView) convertView.findViewById(R.id.model),
                    (TextView) convertView.findViewById(R.id.type));
            convertView.setTag(viewTag);
        } else
        {
            viewTag = (ItemViewTag) convertView.getTag();
        }
        if (mList == null) {
            Log.e("arrival:" , "mlist is null");
        } else if (viewTag.customer_model == null) {
            Log.e("arrival:" , "customer model is null");
        } else if (mList.get(position).customer_model == null) {
            Log.e("arrival:" , "mList customer model is null");
        }
        viewTag.customer_model.setText(mList.get(position).customer_model);
        viewTag.code.setText(mList.get(position).code);
        viewTag.wlzd_code.setText(mList.get(position).wlzd_code);
        viewTag.bar_code.setText(mList.get(position).bar_code);
        viewTag.wlzd_name.setText(mList.get(position).wlzd_name);
        viewTag.model.setText(mList.get(position).model);
        viewTag.type.setText(mList.get(position).type);

        return convertView;
    }

    class ItemViewTag
    {
        public TextView customer_model;//": "ESM09CRI-A1I",   #客户机型
        public TextView code;//": "E1-00101",			#箱号
        public TextView wlzd_code;//": "1501214603",		#物料编号
        public TextView bar_code;//": "624770E100101",	#条形码
        public TextView wlzd_name;//": "电机 FN20N-PG",	#物料名称
        public TextView model;//": "GWC09ZC-K3NNA1A/I 顶(伊莱克斯专用) ", #型号
        public TextView type;//": "02"


        public ItemViewTag(TextView customer_model,TextView code,TextView wlzd_code,
                           TextView bar_code,TextView wlzd_name,TextView model,
                           TextView type)
        {
            this.customer_model = customer_model;
            this.code = code;
            this.wlzd_code = wlzd_code;
            this.bar_code = bar_code;
            this.wlzd_name = wlzd_name;
            this.model = model;
            this.type = type;

        }
    }

    public void setList(ArrayList<ArrivalDetail> list) {
        this.mList = mList;
    }



}

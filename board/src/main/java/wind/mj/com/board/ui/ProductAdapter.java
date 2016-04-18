package wind.mj.com.board.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import wind.mj.com.board.Config;
import wind.mj.com.board.R;
import wind.mj.com.board.net.ListInfo;

/**
 * Created by wind on 16/3/4.
 */
public class ProductAdapter extends BaseAdapter {

    private static final String TAG = ProductAdapter.class.getSimpleName();

    private List<ListInfo> listInfos;
    private LayoutInflater layoutInflater;
    private Context context;

    public ProductAdapter(Context context){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        if (listInfos == null) {
            listInfos = new ArrayList<>();
        }
        for (int i = 0;i < 100;i++) {

            listInfos.add(new ListInfo("MJ20160305","H0901","100","100","1"));
        }
    }
    @Override
    public int getCount() {
        return listInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return listInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listInfos.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_main_product_adapter, null);
            holder.numberView = (TextView) convertView.findViewById(R.id.number_view);
            holder.modelView= (TextView)convertView.findViewById(R.id.model_view);
            holder.workingNumberView = (TextView)convertView.findViewById(R.id.working_number_view);
            holder.packagingNumberView = (TextView) convertView.findViewById(R.id.packaging_number_view);
            holder.statusView = (TextView) convertView.findViewById(R.id.status_view);

            //Log.e(TAG,"" + containers.get(position - 1).getId() +containers.get(position).getCode());
            holder.numberView.setText(listInfos.get(position).number);
            holder.modelView.setText(listInfos.get(position).model);
            holder.workingNumberView.setText(listInfos.get(position).workingNumber);
            holder.packagingNumberView.setText(listInfos.get(position).packagingNumber);
            if (listInfos.get(position).status.equals(Config.STR_ZERO)) {
                holder.statusView.setText(context.getString(R.string.work_on));
                holder.statusView.setTextColor(Color.RED);
            } else {
                holder.statusView.setText(context.getString(R.string.finished));
                holder.statusView.setTextColor(Color.GREEN);
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
            //Log.e(TAG,"" + containers.get(position  - 1).getId() +containers.get(position - 1).getCode());
            holder.numberView.setText(listInfos.get(position).number);
            holder.modelView.setText(listInfos.get(position).model);
            holder.workingNumberView.setText(listInfos.get(position).workingNumber);
            holder.packagingNumberView.setText(listInfos.get(position).packagingNumber);
            if (listInfos.get(position).status.equals(Config.STR_ZERO)) {
                holder.statusView.setText(context.getString(R.string.work_on));
                holder.statusView.setTextColor(Color.RED);
            } else {
                holder.statusView.setText(context.getString(R.string.finished));
                holder.statusView.setTextColor(Color.GREEN);
            }
        }
        return convertView;
    }

    public class ViewHolder {
        public TextView numberView;
        public TextView modelView;
        public TextView workingNumberView;
        public TextView packagingNumberView;
        public TextView statusView;
    }
}

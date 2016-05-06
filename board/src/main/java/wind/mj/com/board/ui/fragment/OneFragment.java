package wind.mj.com.board.ui.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import wind.mj.com.board.R;

public class OneFragment extends Fragment {

    private static final String ARG_PARAM1 = "index";
    private int mParam1;
    private TextView mTitleView;


    public OneFragment() {
        // Required empty public constructor
    }

    public static OneFragment newInstance(int index) {
        OneFragment fragment = new OneFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, index);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        mTitleView = (TextView) view.findViewById(R.id.title);
        mTitleView.setText(R.string.product_name_1);
        return view;
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }

}

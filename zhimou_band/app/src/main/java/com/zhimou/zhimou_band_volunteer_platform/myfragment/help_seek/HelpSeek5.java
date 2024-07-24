package com.zhimou.zhimou_band_volunteer_platform.myfragment.help_seek;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhimou.zhimou_band_volunteer_platform.MainActivity;
import com.zhimou.zhimou_band_volunteer_platform.R;
import com.zhimou.zhimou_band_volunteer_platform.myUtil.myHttp;
/************************************************
 * 动作检测页面
 * *********************************************/
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelpSeek5#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HelpSeek5 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static MainActivity context;

    public HelpSeek5(MainActivity newContext) {
        // Required empty public constructor
        context=newContext;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HelpSeek5.
     */
    // TODO: Rename and change types and number of parameters
    public static HelpSeek5 newInstance(String param1, String param2) {
        HelpSeek5 fragment = new HelpSeek5(context);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.text_fragment, container, false);
        // Inflate the layout for this fragment
        TextView text = view.findViewById(R.id.text_fragment_text);
        text.setText("帮助中，请等待系统评判");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG,"get isFinishede");
        myHttp.myGet(context, myHttp.GET_IS_FINISHED_URL);
    }
}
package com.zhimou.zhimou_band_volunteer_platform.myfragment.help_seek;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.zhimou.zhimou_band_volunteer_platform.R;
/************************************************
 * 无人求助时的页面
 * *********************************************/
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelpSeek1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HelpSeek1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static HelpSeek1 helpSeek1;

    private HelpSeek1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HelpSeek1.
     */
    // TODO: Rename and change types and number of parameters
    public static HelpSeek1 newInstance() {
        if(helpSeek1==null)
        helpSeek1 = new HelpSeek1();
        Bundle args = new Bundle();
        helpSeek1.setArguments(args);
        return helpSeek1;
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
        View view=inflater.inflate(R.layout.text_fragment, container, false);
        TextView textView=view.findViewById(R.id.text_fragment_text);
        textView.setText("当前无人求助");
        // Inflate the layout for this fragment
        return view;
    }
}
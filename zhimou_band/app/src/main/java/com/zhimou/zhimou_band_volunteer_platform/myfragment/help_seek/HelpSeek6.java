package com.zhimou.zhimou_band_volunteer_platform.myfragment.help_seek;

import static java.lang.Thread.sleep;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zhimou.zhimou_band_volunteer_platform.MainActivity;
import com.zhimou.zhimou_band_volunteer_platform.R;
/************************************************
 * 动作检测成功页面
 * *********************************************/
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelpSeek6#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HelpSeek6 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static MainActivity context;

    public HelpSeek6() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HelpSeek6.
     */
    // TODO: Rename and change types and number of parameters
    public static HelpSeek6 newInstance(MainActivity newContext,boolean isFinished) {
        HelpSeek6 fragment = new HelpSeek6();
        context=newContext;
        Bundle args = new Bundle();
        args.putBoolean("isFinished",isFinished);
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
        HelpSeek2.isArrive=false;
        HelpSeek2.isFinish=false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.help_finish_fragment, container, false);
        TextView text=view.findViewById(R.id.help_finish_fragment_text);
        if(getArguments().getBoolean("isFinished")) {
            text.setText("动作检测合格，积分+1");
        }else{
            text.setText("帮助行为未完成");
        }
        view.findViewById(R.id.help_finish_fragment_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.replaceFragment(HelpSeek1.newInstance());
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(3000);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
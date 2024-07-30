package com.zhimou.zhimou_band_volunteer_platform.myfragment.help_seek;

import static java.lang.Thread.sleep;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhimou.zhimou_band_volunteer_platform.MainActivity;
import com.zhimou.zhimou_band_volunteer_platform.R;

import org.w3c.dom.Text;
/************************************************
 * 帮助进行页面
 * *********************************************/
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelpSeek4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HelpSeek4 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MainActivity mainActivity;

    public HelpSeek4() {
        // Required empty public constructor
    }
    public HelpSeek4(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HelpSeek4.
     */
    // TODO: Rename and change types and number of parameters
    public static HelpSeek4 newInstance(String param1, String param2) {
        HelpSeek4 fragment = new HelpSeek4();
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
        View view = inflater.inflate(R.layout.helpseek4_fragment, container, false);
        // Inflate the layout for this fragment
        TextView text=view.findViewById(R.id.helpseek4_text);
        text.setText("您已抵达目的地");

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
                mainActivity.curHelpSeek=new HelpSeek5(mainActivity);
                mainActivity.replaceFragment(mainActivity.curHelpSeek);
            }
        }).start();
    }
}
package com.zhimou.zhimou_band_volunteer_platform.myfragment.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.zhimou.zhimou_band_volunteer_platform.MainActivity;
import com.zhimou.zhimou_band_volunteer_platform.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MineMain#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MineMain extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static MainActivity context;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MineMain() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MineMain.
     */
    // TODO: Rename and change types and number of parameters
    public static MineMain newInstance(MainActivity newContext) {
        MineMain fragment = new MineMain();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        context=newContext;
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
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.mine_main_fragment,container,false);
        Button set= view.findViewById(R.id.toset_bt);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.replaceFragment(MineSet.newInstance());
            }
        });
        return view;
    }
}
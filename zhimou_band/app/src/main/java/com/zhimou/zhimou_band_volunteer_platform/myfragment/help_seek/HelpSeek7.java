package com.zhimou.zhimou_band_volunteer_platform.myfragment.help_seek;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import static com.amap.api.navi.AMapNavi.setApiKey;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.AmapPageType;
import com.zhimou.zhimou_band_volunteer_platform.MainActivity;
import com.zhimou.zhimou_band_volunteer_platform.R;
import com.zhimou.zhimou_band_volunteer_platform.myUtil.myLocation;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelpSeek7#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HelpSeek7 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static MainActivity context;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HelpSeek7() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HelpSeek7.
     */
    // TODO: Rename and change types and number of parameters
    public static HelpSeek7 newInstance(MainActivity newContext) {
        HelpSeek7 fragment = new HelpSeek7();
        Bundle args = new Bundle();
        context=newContext;
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
        View view=inflater.inflate(R.layout.navi_not_finished_fragment, container, false);
        Button bt=view.findViewById(R.id.not_finished_bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AMapLocationClient.updatePrivacyShow(context, true, true);
                AMapLocationClient.updatePrivacyAgree(context, true);
                setApiKey(context, "1561f4e366d5298b11a84a4e632e8715");
                Poi start = new Poi(null, null, null);
//途经点
//                List<Poi> poiList = new ArrayList();
//                poiList.add(new Poi("故宫", new LatLng(39.918058,116.397026), "B000A8UIN8"));
//终点

                Poi end = new Poi(null, new LatLng(context.latitude,context.longitude), null);
                Log.e(TAG,(""+context.latitude)+" "+context.longitude);
// 组件参数配置
                AmapNaviParams params = new AmapNaviParams(start, null, end, AmapNaviType.DRIVER, AmapPageType.ROUTE);
// 启动组件
                AmapNaviPage.getInstance().showRouteActivity(context, params, null);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    private static boolean flag=false;
    @Override
    public void onResume() {
        super.onResume();
        if(flag==true){
            flag=false;
            myLocation.getLocation(getContext(),context);
            //context.replaceFragment(new HelpSeek4(context));
        }else{
            flag=true;
        }
        Log.e(TAG,"onStart");
    }
}
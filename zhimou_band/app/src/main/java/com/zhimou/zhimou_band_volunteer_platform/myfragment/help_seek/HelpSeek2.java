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

import com.amap.api.services.cloud.CloudSearch;
import com.amap.api.services.poisearch.PoiSearch.SearchBound;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.AmapPageType;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.poisearch.PoiSearch;
import com.zhimou.zhimou_band_volunteer_platform.MainActivity;
import com.zhimou.zhimou_band_volunteer_platform.R;
import com.zhimou.zhimou_band_volunteer_platform.myUtil.myHttp;
/************************************************
 * 有人求助的页面
 * *********************************************/
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelpSeek2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HelpSeek2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private final static String IS_ACCEPT_URL="http://39.105.127.195:8082/is_accept?isAccept=true";
    private final static String NOT_ACCEPT_URL="http://39.105.127.195:8082/is_accept?isAccept=false";
    public static Double latitude;
    public static Double longitude;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static MainActivity context;
    public static boolean isArrive =false;
    public static boolean isFinish=false;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HelpSeek2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HelpSeek2.
     */
    // TODO: Rename and change types and number of parameters
    public static HelpSeek2 newInstance(MainActivity newContext) {
        HelpSeek2 fragment = new HelpSeek2();
        Bundle args = new Bundle();
        context=newContext;
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
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
        View view=inflater.inflate(R.layout.helpseek_choose_fragment, container, false);
        Button accept= view.findViewById(R.id.accept_bt);
        Button refuse=view.findViewById(R.id.refuse_bt);

        refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myHttp.myGet(NOT_ACCEPT_URL);
                context.replaceFragment(HelpSeek1.newInstance());
            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isArrive =true;
                myHttp.myGet(IS_ACCEPT_URL);
                AMapLocationClient.updatePrivacyShow(HelpSeek2.context, true, true);
                AMapLocationClient.updatePrivacyAgree(HelpSeek2.context, true);
                setApiKey(HelpSeek2.context, "1561f4e366d5298b11a84a4e632e8715");
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
                AmapNaviPage.getInstance().showRouteActivity(HelpSeek2.context, params, null);
                //启动导航组件
                //AmapNaviPage.getInstance().showRouteActivity(HelpSeek2.context, params, null);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(isArrive==true){
            context.replaceFragment(new HelpSeek4(context));
        }
        Log.e(TAG,"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG,"onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");

    }
}
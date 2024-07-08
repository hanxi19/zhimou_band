package com.zhimou.zhimou_band_volunteer_platform.myfragment.help_seek;

import static android.os.SystemClock.sleep;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.MapView;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.zhimou.zhimou_band_volunteer_platform.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelpSeek3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HelpSeek3 extends Fragment {
    MapView mapView = null;
    AMap aMap;
    public static Double latitude;
    public static Double longitude;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HelpSeek3() {
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
    public static HelpSeek3 newInstance() {
        HelpSeek3 fragment = new HelpSeek3();


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
        Bundle bundle=getArguments();
        latitude=bundle.getDouble("latitude");
        longitude=bundle.getDouble("longitude");
        Log.e(TAG, "newInstance: "+latitude );
        View view=inflater.inflate(R.layout.helpseek_navigation_fragment, container, false);
        // Inflate the layout for this fragment

        //更新隐私合规
        MapsInitializer.updatePrivacyShow(this.getContext(),true,true);
        MapsInitializer.updatePrivacyAgree(this.getContext(),true);
//定义了一个地图view
        mapView = (MapView) view.findViewById(R.id.map);
        if (aMap == null) {
            aMap = mapView.getMap();
        }

        while (longitude==null||latitude==null){
            sleep(100);
            Log.e(TAG, "onCreateView: sleep" );
        }

        // 定义北京市经纬度坐标（此处以北京坐标为例）
        LatLng centerBJPoint= new LatLng(latitude,longitude);
// 定义了一个配置 AMap 对象的参数类
        AMapOptions mapOptions = new AMapOptions();
// 设置了一个可视范围的初始化位置
// CameraPosition 第一个参数： 目标位置的屏幕中心点经纬度坐标。
// CameraPosition 第二个参数： 目标可视区域的缩放级别
// CameraPosition 第三个参数： 目标可视区域的倾斜度，以角度为单位。
// CameraPosition 第四个参数： 可视区域指向的方向，以角度为单位，从正北向顺时针方向计算，从0度到360度
        mapOptions.camera(new CameraPosition(centerBJPoint, 10f, 0, 0));
// 定义一个 MapView 对象，构造方法中传入 mapOptions 参数类
        MapView mapView = new MapView(getContext(), mapOptions);
// 调用 onCreate方法 对 MapView LayoutParams 设置
        mapView.onCreate(savedInstanceState);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}
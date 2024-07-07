package com.zhimou.zhimou_band_volunteer_platform.myUtil;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.zhimou.zhimou_band_volunteer_platform.myfragment.help_seek.HelpSeek2;

public class myLocation {
    //声明AMapLocationClient类对象

    public static AMapLocationClientOption mLocationOption = null;
    public static AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public static AMapLocationListener mLocationListener ;
    //声明AMapLocationClientOption对象
    static public void getLocation(Context context,HelpSeek2 helpSeek2){
        new Thread(){
            @Override
            public void run() {
                super.run();
                //初始化定位
                try {
                    AMapLocationClient.updatePrivacyShow(context, true, true);
                    AMapLocationClient.updatePrivacyAgree(context, true);
                    mLocationClient = new AMapLocationClient(context.getApplicationContext());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mLocationListener= new AMapLocationListener() {
                    @Override
                    public void onLocationChanged(AMapLocation aMapLocation) {
                        Log.e(TAG, "onLocationChanged: "+ aMapLocation.getLatitude());
                        helpSeek2.latitude= aMapLocation.getLatitude();
                        helpSeek2.longitude=aMapLocation.getLongitude();
                        //Log.e(TAG, "address:"+address );
                    }
                };
                //设置定位回调监听
                mLocationClient.setLocationListener(mLocationListener);
                //初始化AMapLocationClientOption对象
                mLocationOption = new AMapLocationClientOption();
                //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
                mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
                //获取一次定位结果：
                //该方法默认为false。
                mLocationOption.setOnceLocation(true);

                //获取最近3s内精度最高的一次定位结果：
                //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
                mLocationOption.setOnceLocationLatest(true);
                //设置是否返回地址信息（默认返回地址信息）
                mLocationOption.setNeedAddress(true);
                //设置是否允许模拟位置,默认为false，不允许模拟位置
                mLocationOption.setMockEnable(false);
                //关闭缓存机制
                mLocationOption.setLocationCacheEnable(false);
                //给定位客户端对象设置定位参数
                mLocationClient.setLocationOption(mLocationOption);
                //启动定位
                mLocationClient.startLocation();

            }
        }.start();

    }
}

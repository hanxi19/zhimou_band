package com.zhimou.zhimou_band_volunteer_platform.myUtil;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.health.connect.datatypes.units.Power;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.zhimou.zhimou_band_volunteer_platform.MainActivity;
import com.zhimou.zhimou_band_volunteer_platform.myfragment.help_seek.HelpSeek2;
import com.zhimou.zhimou_band_volunteer_platform.myfragment.help_seek.HelpSeek3;
import com.zhimou.zhimou_band_volunteer_platform.myfragment.help_seek.HelpSeek4;
import com.zhimou.zhimou_band_volunteer_platform.myfragment.help_seek.HelpSeek7;

public class myLocation {
    //声明AMapLocationClient类对象

    public static AMapLocationClientOption mLocationOption = null;
    public static AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public static AMapLocationListener mLocationListener ;
    private static final double EQUATOR_RADIUS = 6378137;
    //声明AMapLocationClientOption对象
    static public void getLocation(Context context,MainActivity mainActivity){
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
                        Double curLat=aMapLocation.getLatitude();
                        Double curLon=aMapLocation.getLongitude();
                        Log.e(TAG,"lat:"+curLat+" lon:"+curLon);
                        if(myLocation.getDistance1(mainActivity.longitude,mainActivity.latitude,curLon,curLat)<=2000){
                            Fragment fragment=new HelpSeek4(mainActivity);
                            mainActivity.curHelpSeek=fragment;
                            mainActivity.replaceFragment(fragment);
                        }else {
                            Fragment fragment=HelpSeek7.newInstance(mainActivity);
                            mainActivity.curHelpSeek=fragment;
                            mainActivity.replaceFragment(fragment);
                            Log.e(TAG,"replace fragment helpseek7");
                        }
                        //Log.e(TAG, "address:"+address );
                    }
                };
                //设置定位回调监听
                mLocationClient.setLocationListener(mLocationListener);
                //初始化AMapLocationClientOption对象
                mLocationOption = new AMapLocationClientOption();
                //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
                //mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
                //获取一次定位结果：
                //该方法默认为false。
                mLocationOption.setOnceLocation(true);

                //获取最近3s内精度最高的一次定位结果：
                //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
                mLocationOption.setOnceLocationLatest(false);
                //设置是否返回地址信息（默认返回地址信息）
                mLocationOption.setNeedAddress(true);
                //设置是否允许模拟位置,默认为false，不允许模拟位置
                mLocationOption.setMockEnable(true);
                //关闭缓存机制
                mLocationOption.setLocationCacheEnable(false);
                //给定位客户端对象设置定位参数
                mLocationClient.setLocationOption(mLocationOption);
                //启动定位
                mLocationClient.startLocation();

            }
        }.start();

    }
    public static double getDistance1(double longitude1, double latitude1, double longitude2, double latitude2) {
        // 纬度
        double lat1 = Math.toRadians(latitude1);
        double lat2 = Math.toRadians(latitude2);
        // 经度
        double lon1 = Math.toRadians(longitude1);
        double lon2 = Math.toRadians(longitude2);
        // 纬度之差
        double a = lat1 - lat2;
        // 经度之差
        double b = lon1 - lon2;
        // 计算两点距离的公式
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(b / 2), 2)));
        // 弧长乘赤道半径, 返回单位: 米
        s = s * EQUATOR_RADIUS;
        return s;
    }
}

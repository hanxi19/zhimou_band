package com.zhimou.zhimou_band_volunteer_platform.myService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

public class myLocation extends Service implements AMapLocationListener {
    public static final String ACTION_NAME="getLatLon";
    private final String TAG = "LocationService";
    private AMapLocationClient aMapLocationClient = null;
    private Double latitude;
    private Double longitude;

    public myLocation() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final Intent mIntent = new Intent();
        mIntent.setAction(ACTION_NAME);
        //开启一个线程，对数据进行处理
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    locationConfigure(true);
                    Log.e(TAG, "run: 开始获取位置信息");
                    while (true) {
                        //耗时操作：数据处理并保存，向Activity发送广播
                        mIntent.putExtra("latitude", latitude);
                        mIntent.putExtra("longitude", longitude);
                        sendBroadcast(mIntent);
                        Thread.sleep(300);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                String device_addr = aMapLocation.getCountry()//国家信息
                        + aMapLocation.getProvince()//省份信息
                        + aMapLocation.getCity()//城市信息
                        + aMapLocation.getDistrict()//区信息
                        + aMapLocation.getStreet()//街道信息
                        + aMapLocation.getStreetNum();//门牌号信息
                Log.i(TAG, "设备定位成功，设备所在位置：" + device_addr);

                latitude=aMapLocation.getLatitude();
                longitude=aMapLocation.getLongitude();
            } else {
                Log.e(TAG, "设备定位失败-》》》》》》错信息-》》》》" + aMapLocation.getErrorCode() + ", 错误信息:" + aMapLocation.getErrorInfo());
            }
        }

    }

    public void locationConfigure(boolean start) {
        if (start) {
            Log.i(TAG, "aMapLocation：开启定位服务");
            try {
                aMapLocationClient = new AMapLocationClient(getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //设置定位回调监听，这里要实现AMapLocationListener接口，AMapLocationListener接口只有onLocationChanged方法可以实现，用于接收异步返回的定位结果，参数是AMapLocation类型。
            aMapLocationClient.setLocationListener(this);
            //初始化定位参数
            AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
            //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
            aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
            //设置是否返回地址信息（默认返回地址信息）
            aMapLocationClientOption.setNeedAddress(true);
            //设置是否只定位一次,默认为false
            aMapLocationClientOption.setOnceLocation(false);
            //设置是否强制刷新WIFI，默认为强制刷新
            aMapLocationClientOption.setWifiActiveScan(true);
            //设置是否允许模拟位置,默认为false，不允许模拟位置
            aMapLocationClientOption.setMockEnable(false);
            //设置定位间隔,单位毫秒,半小时定位一次
            aMapLocationClientOption.setInterval(1000 * 60 * 30);
            //给定位客户端对象设置定位参数
            aMapLocationClient.setLocationOption(aMapLocationClientOption);
            //启动定位
            aMapLocationClient.startLocation();
        } else {
            if (aMapLocationClient != null) {
                aMapLocationClient.onDestroy();
                Log.i(TAG, "aMapLocation：关闭定位服务");
            }
        }
    }


}
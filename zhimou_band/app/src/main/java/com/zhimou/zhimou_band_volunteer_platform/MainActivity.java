package com.zhimou.zhimou_band_volunteer_platform;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.amap.api.location.AMapLocationClient;
import com.zhimou.zhimou_band_volunteer_platform.myfragment.main_page.MainPageFragment;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_LOCATION_PERMISSION = 0;
    private static final int REQUEST_BODY_SENSOR_PERMISSION = 1;

    private Button mainPageBt;
    private Button helpSeekBt;
    private Button mineBt;
    private Intent mIntent;
    public Double latitude;
    public Double longitude;
    public boolean existHelpSeek = false;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private SensorEventListener sensorEventListener;
    private long lastPeakTime = 0;
    private int peakCount = 0;
    private static final int PEAK_THRESHOLD = 10; // 加速度峰值阈值
    private static final int PEAK_FREQUENCY = 100; // 峰值频率阈值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPageBt = findViewById(R.id.main_page_bt);
        helpSeekBt = findViewById(R.id.help_seek_bt);
        mineBt = findViewById(R.id.mine_bt);

        AMapLocationClient.updatePrivacyShow(this, true, true);
        AMapLocationClient.updatePrivacyAgree(this, true);

        // 初始化传感器管理器
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorEventListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x=event.values[0];
                float y=event.values[1];
                float z=event.values[2];
                float maxValue = Math.max(Math.abs(x), Math.max(Math.abs(y), Math.abs(z)));
                if (maxValue > PEAK_THRESHOLD) {
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - lastPeakTime > 100) { // 100毫秒内达到峰值
                        peakCount++;
                        lastPeakTime = currentTime;
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                //精度变化时的处理
            }
        };
        sensorManager.registerListener(sensorEventListener,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);

        // 检查并请求权限
        checkAndRequestPermissions();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, new MainPageFragment());
        transaction.commit();

        mainPageBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPageBt.setBackground(getResources().getDrawable(R.drawable.main_page_black));
                helpSeekBt.setBackground(getResources().getDrawable(R.drawable.helpseek));
                mineBt.setBackground(getResources().getDrawable(R.drawable.mine));
                replaceFragment(new MainPageFragment());
            }
        });

        helpSeekBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPageBt.setBackground(getResources().getDrawable(R.drawable.main_page));
                helpSeekBt.setBackground(getResources().getDrawable(R.drawable.helpseek_black));
                mineBt.setBackground(getResources().getDrawable(R.drawable.mine));
                // 获取求助信息
                // myHttp.myGet(MainActivity.this, GET_HELP_INFOR_URL);
            }
        });

        mineBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPageBt.setBackground(getResources().getDrawable(R.drawable.main_page));
                helpSeekBt.setBackground(getResources().getDrawable(R.drawable.helpseek));
                mineBt.setBackground(getResources().getDrawable(R.drawable.mine_black));
                replaceFragment(MainPageFragment.newInstance());
            }
        });
    }

    private void checkAndRequestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BODY_SENSORS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.BODY_SENSORS},
                    REQUEST_BODY_SENSOR_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 定位权限申请成功，进入步骤四：开始使用定位功能
            } else {
                // 定位权限申请失败，可以显示一个提示信息给用户
                Toast.makeText(this, "定位权限申请失败", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_BODY_SENSOR_PERMISSION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 传感器权限申请成功，注册传感器监听器
                sensorManager.registerListener(new SensorEventListener() {
                    @Override
                    public void onSensorChanged(SensorEvent event) {
                        float x = event.values[0];
                        float y = event.values[1];
                        float z = event.values[2];
                        float maxValue = Math.max(Math.abs(x), Math.max(Math.abs(y), Math.abs(z)));

                        if (maxValue > PEAK_THRESHOLD) {
                            long currentTime = System.currentTimeMillis();
                            if (currentTime - lastPeakTime > 100) { // 100毫秒内达到峰值
                                peakCount++;
                                lastPeakTime = currentTime;
                            }
                        }
                    }

                    @Override
                    public void onAccuracyChanged(Sensor sensor, int accuracy) {
                        // 精度变化时的处理
                    }
                }, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            } else {
                // 传感器权限申请失败，可以显示一个提示信息给用户
                Toast.makeText(this, "传感器权限申请失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensorManager != null&&accelerometer!=null&&sensorEventListener!=null) {
            sensorManager.unregisterListener(sensorEventListener,accelerometer);
        }
    }
}
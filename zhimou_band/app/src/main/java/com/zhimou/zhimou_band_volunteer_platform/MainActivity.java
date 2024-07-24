package com.zhimou.zhimou_band_volunteer_platform;

import static android.content.ContentValues.TAG;

import static com.amap.api.navi.AMapNavi.setApiKey;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.Manifest;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentActivity;

import com.amap.api.location.AMapLocationClient;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.AmapPageType;
import com.zhimou.zhimou_band_volunteer_platform.myUtil.myHttp;
import com.zhimou.zhimou_band_volunteer_platform.myfragment.help_seek.HelpSeek2;
import com.zhimou.zhimou_band_volunteer_platform.myfragment.help_seek.HelpSeek5;
import com.zhimou.zhimou_band_volunteer_platform.myfragment.help_seek.HelpSeek6;
import com.zhimou.zhimou_band_volunteer_platform.myfragment.main_page.MainPageFragment;
import com.zhimou.zhimou_band_volunteer_platform.myfragment.mine.MineMain;

public class MainActivity extends FragmentActivity {
    private static final int WRITE_COARSE_LOCATION_REQUEST_CODE = 0 ;
    private static final int REQUEST_LOCATION_PERMISSION = 0;
    Button mainPageBt;
    Button helpSeekBt;
    Button mineBt;
    Intent mIntent;
    public Double latitude;
    public Double longitude;
    public boolean existHelpSeek=false;

//    MyReceiver myReceiver;
//    class MyReceiver extends BroadcastReceiver {
//        @Override
//        public void onReceive(Context context, final Intent intent) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    //获取从Service中传来的data
//                    latitude=intent.getDoubleExtra("latitude",0);
//                    longitude=intent.getDoubleExtra("longitude",0);
//                }
//            });
//        }
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
                    && grantResults[2] == PackageManager.PERMISSION_GRANTED
                    && grantResults[3] == PackageManager.PERMISSION_GRANTED) {
                // 定位权限申请成功，进入步骤四：开始使用定位功能
            } else {
                // 定位权限申请失败，可以显示一个提示信息给用户
                Toast.makeText(this, "定位权限申请失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPageBt =findViewById(R.id.main_page_bt);
        helpSeekBt = findViewById(R.id.help_seek_bt);
        mineBt =findViewById(R.id.mine_bt);
        AMapLocationClient.updatePrivacyShow(this, true, true);
        AMapLocationClient.updatePrivacyAgree(this, true);

        // 检查应用是否已经授权定位权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            // 应用已经被授权定位权限，可以进行定位操作
            // 进入步骤四：开始使用定位功能
        } else {
            // 应用未被授权定位权限，进入步骤二：请求获取定位权限
            // 请求获取定位权限
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION ,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_LOCATION_PERMISSION);
        }

//        mIntent = new Intent(this, myLocation.class);
//        startService(mIntent);
//        myReceiver = new MyReceiver();
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(myLocation.ACTION_NAME);
//        //注册广播
//        registerReceiver(myReceiver, intentFilter, Context.RECEIVER_NOT_EXPORTED);

//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                String str;
//                try {
//                    myLocation.getLocation(MainActivity.this);
//                    while (address==null){
//                        sleep(100);
//                    }
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                Log.e(TAG, "onCreate: "+address);
//            }
//        }.start();

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction Transaction=fragmentManager.beginTransaction();
        Transaction.replace(R.id.fragment_container,MainPageFragment.newInstance());
        Transaction.commit();
        mainPageBt.setBackground(getResources().getDrawable(R.drawable.main_page_black));

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
                if(HelpSeek2.isArrive==false&&HelpSeek2.isFinish==false) {
                    //获取求助信息
                    myHttp.myGet(MainActivity.this, myHttp.GET_HELP_INFOR_URL);
                }else if(HelpSeek2.isArrive==true&&HelpSeek2.isFinish==false){
                    replaceFragment(new HelpSeek5(MainActivity.this));
                }
                //构建导航组件配置类，没有传入起点，所以起点默认为 “我的位置”
                //起点
//                AMapLocationClient.updatePrivacyShow(MainActivity.this, true, true);
//                AMapLocationClient.updatePrivacyAgree(MainActivity.this, true);
//                setApiKey(MainActivity.this, "1561f4e366d5298b11a84a4e632e8715");
//                Poi start = new Poi(null, null, "B000A28DAE");
////途经点
////                List<Poi> poiList = new ArrayList();
////                poiList.add(new Poi("故宫", new LatLng(39.918058,116.397026), "B000A8UIN8"));
////终点
//                Poi end = new Poi(null, new LatLng(39.941823,116.426319), "B000A816R6");
//// 组件参数配置
//                AmapNaviParams params = new AmapNaviParams(null, null, end, AmapNaviType.DRIVER, AmapPageType.ROUTE);
//// 启动组件
//                AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(), params, null);
//                //启动导航组件
//                AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(), params, null);
            }
        });

        mineBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPageBt.setBackground(getResources().getDrawable(R.drawable.main_page));
                helpSeekBt.setBackground(getResources().getDrawable(R.drawable.helpseek));
                mineBt.setBackground(getResources().getDrawable(R.drawable.mine_black));
                //replaceFragment(MineMain.newInstance(MainActivity.this));
                myHttp.myGet(MainActivity.this,myHttp.GET_POINT_URL);
            }
        });
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction Transaction=fragmentManager.beginTransaction();
        Transaction.replace(R.id.fragment_container,fragment);
        Transaction.commit();
        Log.e(TAG, "replaceFragment: commit fibished");
    }
}


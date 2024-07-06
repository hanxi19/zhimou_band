package com.zhimou.zhimou_band;

import static android.content.ContentValues.TAG;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentActivity;

import com.zhimou.zhimou_band.myUtil.myLocation;
import com.zhimou.zhimou_band.myfragment.help_seek.HelpSeek1;
import com.zhimou.zhimou_band.myfragment.help_seek.HelpSeek2;
import com.zhimou.zhimou_band.myfragment.main_page.MainPageFragment;
import com.zhimou.zhimou_band.myfragment.mine.MineMain;

public class MainActivity extends FragmentActivity {
    Button mainPageBt;
    Button helpSeekBt;
    Button mineBt;

    public String address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPageBt =findViewById(R.id.main_page_bt);
        helpSeekBt = findViewById(R.id.help_seek_bt);
        mineBt =findViewById(R.id.mine_bt);

        new Thread(){
            @Override
            public void run() {
                super.run();
                String str;
                try {
                    myLocation.getLocation(MainActivity.this);
                    while (address==null){
                        sleep(100);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Log.e(TAG, "onCreate: "+address);
            }
        }.start();

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction Transaction=fragmentManager.beginTransaction();
        Transaction.replace(R.id.fragment_container,new MainPageFragment());
        Transaction.commit();

        mainPageBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new MainPageFragment());
            }
        });

        helpSeekBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new HelpSeek2());
            }
        });

        mineBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new MineMain());
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
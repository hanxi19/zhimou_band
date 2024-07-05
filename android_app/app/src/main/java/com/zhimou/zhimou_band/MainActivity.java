package com.zhimou.zhimou_band;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import myfragment.help_seek.HelpSeek1;
import myfragment.main_page.MainPageFragment;
import myfragment.mine.MineMain;

public class MainActivity extends FragmentActivity {
    Button mainPageBt;
    Button helpSeekBt;
    Button mineBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPageBt =findViewById(R.id.main_page_bt);
        helpSeekBt = findViewById(R.id.help_seek_bt);
        mineBt =findViewById(R.id.mine_bt);

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
                replaceFragment(new HelpSeek1());
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
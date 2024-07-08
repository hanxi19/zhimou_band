package com.zhimou.zhimou_band_volunteer_platform.myUtil;

import android.os.Bundle;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhimou.zhimou_band_volunteer_platform.MainActivity;
import com.zhimou.zhimou_band_volunteer_platform.myfragment.help_seek.HelpSeek1;
import com.zhimou.zhimou_band_volunteer_platform.myfragment.help_seek.HelpSeek2;
import com.zhimou.zhimou_band_volunteer_platform.myfragment.help_seek.HelpSeek3;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class myHttp {
    static OkHttpClient client = new OkHttpClient();
    private final static String GET_HELP_INFOR_URL="http://10.0.2.2:4523/m2/4623205-4273246-default/191549940";
    public static void myGet(MainActivity context,String url) {
        if(Objects.equals(url, GET_HELP_INFOR_URL)) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    Request request = new Request.Builder()
                        .url(url)
                        .build();
                    try {
                        Response response = client.newCall(request).execute();
                        String body=response.body().string();
                        //Log.i(TAG, "responseBody "+response.body().string());
                        JSONObject json = JSONArray.parseObject(body);
                        if(json.getJSONObject("data").getBoolean("existHelpSeek")) {
                            context.latitude = json.getJSONObject("data").getDouble("latitude");
                            context.longitude = json.getJSONObject("data").getDouble("longitude");
                            context.existHelpSeek=true;

                            Bundle bundle=new Bundle();
                            bundle.putDouble("latitude",context.latitude);
                            bundle.putDouble("longitude",context.longitude);
                            HelpSeek2 fg=HelpSeek2.newInstance(context);
                            fg.setArguments(bundle);
                            context.replaceFragment(fg);

                        }
                        else {
                            context.existHelpSeek=false;
                            context.replaceFragment(HelpSeek1.newInstance());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}

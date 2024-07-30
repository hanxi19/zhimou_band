package com.zhimou.zhimou_band_volunteer_platform.myUtil;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import static java.lang.Thread.sleep;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhimou.zhimou_band_volunteer_platform.MainActivity;
import com.zhimou.zhimou_band_volunteer_platform.myfragment.help_seek.HelpSeek1;
import com.zhimou.zhimou_band_volunteer_platform.myfragment.help_seek.HelpSeek2;
import com.zhimou.zhimou_band_volunteer_platform.myfragment.help_seek.HelpSeek3;
import com.zhimou.zhimou_band_volunteer_platform.myfragment.help_seek.HelpSeek6;
import com.zhimou.zhimou_band_volunteer_platform.myfragment.mine.MineMain;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class myHttp {
    static OkHttpClient client = new OkHttpClient();
    //服务器URL
    public final static String GET_HELP_INFOR_URL="http://39.105.127.195:8082/get_help_infor";
    public final static String IS_ACCEPT_URL="http://39.105.127.195:8082/is_accept?isAccept=true";
    public final static String NOT_ACCEPT_URL="http://39.105.127.195:8082/is_accept?isAccept=false";
    public final static String GET_POINT_URL="http://39.105.127.195:8082/point";
    public final static String GET_IS_FINISHED_URL="http://39.105.127.195:8082/get_help_finished";
    //本地测试URL
//    public final static String GET_HELP_INFOR_URL="http://10.0.2.2:8082/get_help_infor";
//    public final static String IS_ACCEPT_URL="http://10.0.2.2:8082/is_accept?isAccept=true";
//    public final static String NOT_ACCEPT_URL="http://10.0.2.2:8082/is_accept?isAccept=false";
//    public final static String GET_POINT_URL="http://10.0.2.2:8082/point";
//    public final static String GET_IS_FINISHED_URL="http://10.0.2.2:8082/get_help_finished";
    public static void myGet(String url){
        if(url.equals(IS_ACCEPT_URL)||url.equals(NOT_ACCEPT_URL)) {
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
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
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
                            context.normalType= json.getJSONObject("data").getObject("normalType",Integer.class);
                            context.existHelpSeek=true;

                            Bundle bundle=new Bundle();
                            bundle.putDouble("latitude",context.latitude);
                            bundle.putDouble("longitude",context.longitude);
                            HelpSeek2 fg=HelpSeek2.newInstance(context,context.normalType);
                            fg.setArguments(bundle);
                            context.curHelpSeek=fg;
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
        }else if(url.equals(GET_POINT_URL)){
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
                        int point=json.getInteger("data");
                        context.replaceFragment(MineMain.newInstance(context,point));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else if (url.equals(GET_IS_FINISHED_URL)) {
            Log.e(TAG,"get isFinished");
            new Thread(new Runnable() {

                @Override
                public void run() {
                    while (true)
                    {
                        Request request = new Request.Builder()
                                .url(url)
                                .build();
                        try {
                            Response response = client.newCall(request).execute();
                            String body = response.body().string();
                            //Log.i(TAG, "responseBody "+response.body().string());
                            JSONObject json = JSONArray.parseObject(body);
                            Integer isFinished = json.getInteger("data");
                            Log.e(TAG,"isFinished:"+isFinished);
                            if (isFinished.equals(1)) {
                                context.curHelpSeek=HelpSeek6.newInstance(context, true);
                                context.replaceFragment(HelpSeek6.newInstance(context, true));
                                break;
                            } else if (isFinished.equals(2)) {
                                context.curHelpSeek=HelpSeek6.newInstance(context, false);
                                context.replaceFragment(HelpSeek6.newInstance(context, false));
                                break;
                            }
                            sleep(1000);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }).start();
        }
    }
}

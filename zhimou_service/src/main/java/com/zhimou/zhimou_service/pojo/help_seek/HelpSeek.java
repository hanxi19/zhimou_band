package com.zhimou.zhimou_service.pojo.help_seek;

import java.util.List;

public class HelpSeek {
    private static Double latitude;
    private static Double longitude;
    private static boolean isArrive=false;
    private static int actFinished=0; //0:判断中 1:完成 2:未完成
    private static Integer helpType=null; //0: 主动普通求助 1：主动紧急求助 2：被动求助
    private static Integer normalType=null; //0:购物 1：迷路 2：非普通求助
    private static List<Integer> heartRate=null;
    private static List<Integer> bloodOxygen=null;
    public static int getHelpType() {
        return helpType;
    }

    public static void setHelpType(int helpType) {
        HelpSeek.helpType = helpType;
    }

    public static int getNormalType() {
        return normalType;
    }

    public static void setNormalType(Integer normalType) {
        HelpSeek.normalType = normalType;
    }

    public static List<Integer> getHeartRate() {
        return heartRate;
    }

    public static void setHeartRate(List<Integer> heartRate) {
        HelpSeek.heartRate = heartRate;
    }

    public static List<Integer> getBloodOxygen() {
        return bloodOxygen;
    }

    public static void setBloodOxygen(List<Integer> bloodOxygen) {
        HelpSeek.bloodOxygen = bloodOxygen;
    }


    private HelpSeek(){}
    private HelpSeek(Double newLatitude, Double newLongitude){
        latitude=newLatitude;
        longitude=newLongitude;
    }
    private static class LocatorHolder{
        private static HelpSeek instance=null;
        private static void setInstance(Double latitude,Double longitude){
            instance=new HelpSeek(latitude,longitude);
        }
    }
    public static void arrive(){
        isArrive=true;
    }
    public static void finish(){
        actFinished=1;
    }
    public static void notFinish(){actFinished=2;}
    public static boolean getArrive(){
        return isArrive;
    }
    public static int getFinished(){
        return actFinished;
    }
    public static void setLoacator(Double latitude,Double longitude,Integer newHelpType,Integer newNormalType){
        HelpSeek.LocatorHolder.setInstance(latitude,longitude);
        helpType=newHelpType;
        normalType=newNormalType;
    }
    public static void clearLocator(){
        LocatorHolder.instance=null;
        isArrive=false;
        actFinished=0;
        helpType=-1;
        normalType=-1;
        bloodOxygen=null;
        heartRate=null;
    }
    public static HelpSeek getLocator(){
        return LocatorHolder.instance;
    }
    public static Double getLatitude(){
        return latitude;
    }
    public static Double getLongitude(){
        return longitude;
    }
}

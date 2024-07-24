package com.zhimou.zhimou_service.pojo.help_seek;

public class HelpSeek {
    private static Double latitude;
    private static Double longitude;
    private static boolean isArrive=false;
    private static int actFinished=0; //0:判断中 1:完成 2:未完成
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
    public static void setLoacator(Double latitude,Double longitude){
        HelpSeek.LocatorHolder.setInstance(latitude,longitude);
    }
    public static void clearLocator(){
        LocatorHolder.instance=null;
        isArrive=false;
        actFinished=0;
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

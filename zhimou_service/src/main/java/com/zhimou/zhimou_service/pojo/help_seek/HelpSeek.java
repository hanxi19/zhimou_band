package com.zhimou.zhimou_service.pojo.help_seek;

public class HelpSeek {
    private static Double latitude;
    private static Double longitude;
    private static boolean isArrive=false;
    private static boolean actFinished=false;
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
        actFinished=true;
    }
    public static boolean getArrive(){
        return isArrive;
    }
    public static boolean getFinished(){
        return actFinished;
    }
    public static void setLoacator(Double latitude,Double longitude){
        HelpSeek.LocatorHolder.setInstance(latitude,longitude);
    }
    public static void clearLocator(){
        LocatorHolder.instance=null;
        isArrive=false;
        actFinished=false;
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

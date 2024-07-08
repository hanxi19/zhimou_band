package com.zhimou.zhimou_band_volunteer_platform.myBean;

public class LatLonBean {
    private int code;
    private String msg;
    private Data data;

    public LatLonBean(int code, String msg, Data data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LatLonBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    class Data{
        private Double latitude;
        private Double Longitude;

        public Data(Double latitude, Double longitude) {
            this.latitude = latitude;
            Longitude = longitude;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return Longitude;
        }

        public void setLongitude(Double longitude) {
            Longitude = longitude;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "latitude=" + latitude +
                    ", Longitude=" + Longitude +
                    '}';
        }
    }


}

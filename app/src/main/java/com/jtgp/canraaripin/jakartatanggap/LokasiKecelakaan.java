package com.jtgp.canraaripin.jakartatanggap;

import com.google.gson.annotations.SerializedName;

public class LokasiKecelakaan {

    @SerializedName("id")
    private String id;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("codezip")
    private String codezip;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("longtitude")
    private String longtitude;
    @SerializedName("namars")
    private String namars;

    public LokasiKecelakaan(){}

    public LokasiKecelakaan(String id, String alamat, String codezip, String latitude, String longtitude, String namars){
        this.id = id;
        this.alamat = alamat;
        this.codezip = codezip;
        this.latitude = latitude;
        this.latitude = longtitude;
        this.namars = namars;
    }
    // get data
    public String getId() {
        return id;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getCodezip() {
        return codezip;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getNamars() {
        return namars;
    }
    //set data

    public void setId(String id) {
        this.id = id;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setCodezip(String codezip) {
        this.codezip = codezip;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setNamars(String namars) {
        this.namars = namars;
    }

}

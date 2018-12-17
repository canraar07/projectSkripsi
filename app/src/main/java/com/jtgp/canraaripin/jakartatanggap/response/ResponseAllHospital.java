package com.jtgp.canraaripin.jakartatanggap.response;

  import com.google.gson.annotations.Expose;
  import com.google.gson.annotations.SerializedName;

public class ResponseAllHospital {

    @SerializedName("idpemadam")
    @Expose
    private String idpemadam;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("telpon")
    @Expose
    private String telpon;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longtitude")
    @Expose
    private String longtitude;
    @SerializedName("namars")
    @Expose
    private String namars;

    public String getIdpemadam() {
        return idpemadam;
    }

    public void setIdpemadam(String idpemadam) {
        this.idpemadam = idpemadam;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelpon() {
        return telpon;
    }

    public void setTelpon(String telpon) {
        this.telpon = telpon;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getNamars() {
        return longtitude;
    }

    public void setNamars(String namars) {
        this.namars = namars;
    }

    public ResponseAllHospital(String idpemadam, String alamat, String telpon, String latitude, String longtitude,String namars){

        this.idpemadam = idpemadam;
        this.alamat = alamat;
        this.telpon = telpon;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.namars = namars;
    }
}

package com.jtgp.canraaripin.jakartatanggap.util.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class resultAdd {
    @SerializedName("erorcode")
    @Expose
    private Integer erorcode;
    @SerializedName("errmsg")
    @Expose
    private String errmsg;

    public Integer getErorcode() {
        return erorcode;
    }

    public void setErorcode(Integer erorcode) {
        this.erorcode = erorcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}

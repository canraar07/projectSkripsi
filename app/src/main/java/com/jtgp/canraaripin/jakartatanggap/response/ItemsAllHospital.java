package com.jtgp.canraaripin.jakartatanggap.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemsAllHospital {
    @SerializedName("data")
    public List<ResponseAllHospital>data;

    public List<ResponseAllHospital> getItems() {
        return data;
    }

    public void setItems(List<ResponseAllHospital> items) {
        this.data = items;
    }

    public ItemsAllHospital(List<ResponseAllHospital>items){
        this.data = items;
    }
}

package com.jtgp.canraaripin.jakartatanggap.util.api;

import com.jtgp.canraaripin.jakartatanggap.response.ItemsAllHospital;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Baseservice {

    //Fungsi memanggil add lokasi kejadian
    @FormUrlEncoded
    @POST("add_location.php")
    Call<ResponseBody>add_locationInsiden(@Field("id") String id,
                                          @Field("alamat") String alamat,
                                          @Field("codezip") String codezip,
                                          @Field("latitude") Double latitude,
                                          @Field("longtitude") Double longtitude);

    //Get Semua Alamat Pemadam
    @GET("listPmdkJakpus.php")
    Call<ItemsAllHospital>getData();


}

package com.jtgp.canraaripin.jakartatanggap.util.api;

public class UtilAPI {
    public static final String BASE_URL_API = "https://jkttgp.000webhostapp.com/";

    // Mendeklarasikan Interface BaseApiService
    public static Baseservice getAPIService(){
        return RetropitClient.getClient(BASE_URL_API).create(Baseservice.class);
    }
}

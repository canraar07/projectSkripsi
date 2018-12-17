package com.jtgp.canraaripin.jakartatanggap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.places.ui.PlacePicker;
import com.jtgp.canraaripin.jakartatanggap.response.ItemsAllHospital;
import com.jtgp.canraaripin.jakartatanggap.response.ResponseAllHospital;
import com.jtgp.canraaripin.jakartatanggap.util.api.Baseservice;
import com.jtgp.canraaripin.jakartatanggap.util.api.UtilAPI;

import java.lang.reflect.Array;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeMenu extends AppCompatActivity {
    ProgressDialog loading;
    Baseservice mApiservice;
    TextView textEror;
    String test;
    Array testArr;
    private ConstraintLayout clickMenufirefighter;//ConstraintLayout
    private ConstraintLayout clickMenup3k;
    private int PLACE_PICKER_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mApiservice = UtilAPI.getAPIService();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);
        //getDataPemadam();
        clickMenufirefighter = (ConstraintLayout) findViewById(R.id.bmenupemadam);
        clickMenup3k = (ConstraintLayout) findViewById(R.id.p3kbutton);
        textEror  = (TextView) findViewById(R.id.textView5);
        clickMenufirefighter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                Intent pindah = new Intent(getApplicationContext(),MainMenu.class);
                startActivity(pindah);
            }
        });

        clickMenup3k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindahp3k = new Intent(getApplicationContext(),mainMenup3k.class);
                startActivity(pindahp3k);
            }
        });
    }

    public void getDataPemadam(){
        loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);

        Call<ItemsAllHospital> call = mApiservice.getData();
        call.enqueue(new Callback<ItemsAllHospital>() {
            @Override
            public void onResponse(Call<ItemsAllHospital> call, Response<ItemsAllHospital> response) {
                loading.dismiss();
                List<ResponseAllHospital> rspn = response.body().getItems();
                loadDt(rspn);

            }

            @Override
            public void onFailure(Call<ItemsAllHospital> call, Throwable t) {
                loading.dismiss();

            }
        });
    }
    private void loadDt(List<ResponseAllHospital> rspn){
        //textEror = rspn.size()
        Toast.makeText(getApplicationContext(),"Lat : "+rspn.size(),Toast.LENGTH_LONG).show();
       for(ResponseAllHospital data: rspn){

            //test = data.getAlamat();
            //textEror.setText(data.getAlamat());
        }
        textEror.setText(test);

    }
}

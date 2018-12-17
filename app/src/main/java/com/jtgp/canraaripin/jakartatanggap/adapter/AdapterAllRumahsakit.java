package com.jtgp.canraaripin.jakartatanggap.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class AdapterAllRumahsakit extends RecyclerView.Adapter<AdapterAllRumahsakit.AdapterPemadamHolder>{

     List<AllHospitalItem> semuaPemadamList;
    //List<SemuamatkulItem> semuamatkulItemList;

    @NonNull
    @Override
    public AdapterPemadamHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPemadamHolder adapterPemadamHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class AdapterPemadamHolder extends RecyclerView.ViewHolder {
        public AdapterPemadamHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

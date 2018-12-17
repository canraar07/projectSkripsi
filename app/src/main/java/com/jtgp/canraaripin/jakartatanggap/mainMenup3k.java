package com.jtgp.canraaripin.jakartatanggap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class mainMenup3k extends AppCompatActivity {

    String posisionList,detailp3k;
    ListView listview;
    ArrayAdapter<CharSequence> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_p3k);
        setContentView(R.layout.activity_main_menup3k);
        listview = findViewById(R.id.listViewP3k);
        adapter = ArrayAdapter.createFromResource(this,R.array.p3karray,android.R.layout.simple_list_item_1);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posisionList = String.valueOf(adapter.getItem(position));
                actionSetDetailP3k(posisionList);
            }
        });
    }

    public void actionSetDetailP3k(String position){
        Intent pindahDetailP3k = new Intent(getApplicationContext(),contentP3K.class);
        Bundle bundle = new Bundle();
        if(position.equals("Jika korban pingsan")) {
            detailp3k = getString(R.string.desc_pingsan);
            bundle.putString("titlep3k", position);
            bundle.putString("dtlPingsan", detailp3k);
        }else if(position.equals("Jika korban mengalami syok")) {
            detailp3k = getString(R.string.desc_sock);
            bundle.putString("titlep3k", position);
            bundle.putString("dtlPingsan", detailp3k);
        }else if(position.equals("Jika korban mengalami keseleo")) {
            detailp3k = getString(R.string.desc_kseleo);
            bundle.putString("titlep3k", position);
            bundle.putString("dtlPingsan", detailp3k);
        }else if(position.equals("Jika korban mengalami patah tulang")) {
            detailp3k = getString(R.string.desc_patahtulang);
            bundle.putString("titlep3k", position);
            bundle.putString("dtlPingsan", detailp3k);
        }else if(position.equals("Jika korban mengalami luka bakar")) {
            detailp3k = getString(R.string.desc_lukabakar);
            bundle.putString("titlep3k", position);
            bundle.putString("dtlPingsan", detailp3k);
        }else if(position.equals("Jika korban mengalami pendarahan")) {
            detailp3k = getString(R.string.desc_pendarahan);
            bundle.putString("titlep3k", position);
            bundle.putString("dtlPingsan", detailp3k);
        }
        else{
            detailp3k = "";
            bundle.putString("titlep3k", position);
            bundle.putString("dtlPingsan", "");
        }
        pindahDetailP3k.putExtras(bundle);
        startActivity(pindahDetailP3k);
    }

}

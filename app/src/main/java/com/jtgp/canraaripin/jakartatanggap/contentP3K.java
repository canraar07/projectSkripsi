package com.jtgp.canraaripin.jakartatanggap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class contentP3K extends AppCompatActivity {
    TextView textTitlep3k,detailP3K;
    String title,detailP3kstring;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_p3_k);
        Bundle getBundle = getIntent().getExtras();
        title = getBundle.getString("titlep3k");
        setTitle(title);
        detailP3kstring = getBundle.getString("dtlPingsan");
        textTitlep3k = findViewById(R.id.title_p3k_item);
        detailP3K = findViewById(R.id.id_desc);
        textTitlep3k.setText(title);
        detailP3K.setText(detailP3kstring);

    }
}

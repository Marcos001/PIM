package com.trairas.nig.pim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class about extends AppCompatActivity {

    TextView tx_about_description;

    public boolean init_text_view_description(){
        if (tx_about_description==null){
            tx_about_description = (TextView) findViewById(R.id.tv_about_description);
            tx_about_description.setText(R.string.tx_description);
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_about);

        init_text_view_description();

    }
}

package com.example.elaboratocookaroo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.example.elaboratocookaroo.Adapter.PhoneListAdapter;
import com.example.elaboratocookaroo.Common.Methods;
import com.example.elaboratocookaroo.R;
import com.example.elaboratocookaroo.Request.PhoneList;
import com.google.android.gms.common.internal.service.Common;

public class PrenotaPosti extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenota_posti);
        PhoneList r=new PhoneList("192.168.1.9",5000);
        Methods.SJ(r);
        ListView listView=findViewById(R.id.lv);
        listView.setAdapter(new PhoneListAdapter(PrenotaPosti.this,r.getSedeInfos()));
    }
}
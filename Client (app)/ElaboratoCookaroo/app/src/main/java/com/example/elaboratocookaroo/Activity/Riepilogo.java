package com.example.elaboratocookaroo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.example.elaboratocookaroo.Adapter.RiepilogoAdapter;
import com.example.elaboratocookaroo.Common.Variables;
import com.example.elaboratocookaroo.R;

public class Riepilogo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riepilogo);
        ListView listView=findViewById(R.id.lv);
        listView.setAdapter(new RiepilogoAdapter(Riepilogo.this,CartActivity.cartAdapter.getItems()));
        Button button=findViewById(R.id.home);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
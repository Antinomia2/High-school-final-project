package com.example.elaboratocookaroo.Activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.example.elaboratocookaroo.Adapter.IntroAdapter;
import com.example.elaboratocookaroo.R;

public class Intro extends AppCompatActivity {
    private ViewPager vip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        vip=findViewById(R.id.vp);
        IntroAdapter adapter=new IntroAdapter(getSupportFragmentManager());
        vip.setAdapter(adapter);
    }
}
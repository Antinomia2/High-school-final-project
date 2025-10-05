package com.example.elaboratocookaroo.Fragment.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.elaboratocookaroo.Activity.LoginActivity;
import com.example.elaboratocookaroo.R;

public class ThirdFragment extends Fragment {
    private TextView back;
    private Button done;
    private ViewPager vip;
    public ThirdFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_third, container, false);
        vip=getActivity().findViewById(R.id.vp);
        back=view.findViewById(R.id.slidethreeback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vip.setCurrentItem(1);
            }
        });
        done=view.findViewById(R.id.slidethreedone);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), LoginActivity.class));
                getActivity().finish();
            }
        });
        return view;
    }
}
package com.example.elaboratocookaroo.Fragment.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.elaboratocookaroo.R;

public class SecondFragment extends Fragment {
    private TextView next;
    private TextView back;
    private ViewPager vip;
    public SecondFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_second, container, false);
        vip=getActivity().findViewById(R.id.vp);
        next=view.findViewById(R.id.sildetwonext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vip.setCurrentItem(2);
            }
        });
        back=view.findViewById(R.id.slidetwoback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vip.setCurrentItem(0);
            }
        });
        return view;
    }
}
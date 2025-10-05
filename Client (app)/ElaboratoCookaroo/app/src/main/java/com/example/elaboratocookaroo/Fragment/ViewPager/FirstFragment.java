package com.example.elaboratocookaroo.Fragment.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.elaboratocookaroo.R;

public class FirstFragment extends Fragment {
    private TextView next;
    private ViewPager vip;
    public FirstFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     View view=inflater.inflate(R.layout.fragment_first, container, false);
     vip=getActivity().findViewById(R.id.vp);
     next=view.findViewById(R.id.sildeonenext);
     next.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             vip.setCurrentItem(1);
         }
     });
     return view;
    }
}
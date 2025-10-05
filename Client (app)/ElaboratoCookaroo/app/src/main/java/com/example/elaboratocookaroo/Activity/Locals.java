package com.example.elaboratocookaroo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.elaboratocookaroo.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Locals extends AppCompatActivity implements OnMapReadyCallback {
    private int callphonepermission=1;
    private GoogleMap map;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locals);
        textView=findViewById(R.id.sede);
        SupportMapFragment mapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sede1=new LatLng(40.730610, -73.935242);
        LatLng sede2=new LatLng(45.52117987149628, 9.15722777823229);
        LatLng sede3=new LatLng(45.42117987149628, 9.25722777823229);
        LatLng sede4=new LatLng(41.902782, 12.496366);
        LatLng sede5=new LatLng(45.116177, 7.742615);
        LatLng sede6=new LatLng(40.853294, 14.305573);
        LatLng sede7=new LatLng(51.509865, -0.118092);
        map=googleMap;
        map.addMarker(new MarkerOptions().position(sede1).title("Sede New York"));
        map.addMarker(new MarkerOptions().position(sede2).title("Sede Milano 2"));
        map.addMarker(new MarkerOptions().position(sede3).title("Sede Milano 1"));
        map.addMarker(new MarkerOptions().position(sede4).title("Sede Roma"));
        map.addMarker(new MarkerOptions().position(sede5).title("Sede Torino"));
        map.addMarker(new MarkerOptions().position(sede6).title("Sede Napoli"));
        map.addMarker(new MarkerOptions().position(sede7).title("Sede Londra"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sede2));
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                BottomSheetDialog bottomsheetdialog=new BottomSheetDialog(Locals.this);
                bottomsheetdialog.setContentView(R.layout.bottom_sheet_dialog);
                bottomsheetdialog.setCanceledOnTouchOutside(false);
                Button chiama=bottomsheetdialog.findViewById(R.id.chiama);
                chiama.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(ContextCompat.checkSelfPermission(Locals.this, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED)
                            startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:0266222804")));
                        else
                            ActivityCompat.requestPermissions(Locals.this,new String[]{Manifest.permission.CALL_PHONE},callphonepermission);
                    }
                });
                bottomsheetdialog.show();
                return false;
            }
        });
    }
}
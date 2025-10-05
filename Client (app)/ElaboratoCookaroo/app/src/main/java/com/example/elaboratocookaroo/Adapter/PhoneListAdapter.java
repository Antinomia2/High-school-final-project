package com.example.elaboratocookaroo.Adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.example.elaboratocookaroo.Object.SedeInfo;
import com.example.elaboratocookaroo.R;

public class PhoneListAdapter extends ArrayAdapter<SedeInfo> {
    private int callphonepermission=1;
    private Context c;
    private SedeInfo[] sedeInfos;
    public PhoneListAdapter(Context c,SedeInfo[] sedeInfos){
        super(c, R.layout.row_phonenumber,R.id.Username,sedeInfos);
        this.c=c;
        this.sedeInfos=sedeInfos;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater) c.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=layoutInflater.inflate(R.layout.row_phonenumber,parent,false);
        TextView sede=row.findViewById(R.id.sede);
        TextView phone=row.findViewById(R.id.phone);
        ImageView call=row.findViewById(R.id.call);
        sede.setText(sedeInfos[position].getSede());
        phone.setText(sedeInfos[position].getPhone());
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(c, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED)
                    c.startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+sedeInfos[position].getPhone())));
                else
                    ActivityCompat.requestPermissions((Activity) c,new String[]{Manifest.permission.CALL_PHONE},callphonepermission);
            }
        });
        return row;
    }
}

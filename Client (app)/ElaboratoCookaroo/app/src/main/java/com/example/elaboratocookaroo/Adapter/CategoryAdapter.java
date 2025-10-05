package com.example.elaboratocookaroo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.example.elaboratocookaroo.R;
import com.example.elaboratocookaroo.Object.Category;

public class CategoryAdapter extends ArrayAdapter<Category> {
    private Context c;
    private Category[] categories;
    public CategoryAdapter(Context c,Category[] categories){
        super(c, R.layout.row_category,R.id.category,categories);
        this.c=c;
        this.categories=categories;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater) c.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=layoutInflater.inflate(R.layout.row_category,parent,false);
        ImageView imageView=row.findViewById(R.id.image);
        TextView textView=row.findViewById(R.id.category);
        Glide.with(c).load(categories[position].getImage()).into(imageView);
        textView.setText(categories[position].getName());
        return row;
    }
}

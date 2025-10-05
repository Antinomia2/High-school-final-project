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
import com.example.elaboratocookaroo.Object.FoodInfo.FoodItem;
import com.example.elaboratocookaroo.R;

public class FoodAdapter extends ArrayAdapter<FoodItem> {
    private Context c;
    private FoodItem[] foodItems;
    public FoodAdapter(Context c,FoodItem[] foodItems){
        super(c, R.layout.row_food,R.id.name,foodItems);
        this.c=c;
        this.foodItems=foodItems;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater) c.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=layoutInflater.inflate(R.layout.row_food,parent,false);
        ImageView imageView=row.findViewById(R.id.image);
        TextView name=row.findViewById(R.id.name);
        TextView price=row.findViewById(R.id.price);
        Glide.with(c).load(foodItems[position].getImage()).into(imageView);
        name.setText(foodItems[position].getName());
        price.setText(Float.toString(foodItems[position].getPrezzo())+" £");
        return row;
    }
}

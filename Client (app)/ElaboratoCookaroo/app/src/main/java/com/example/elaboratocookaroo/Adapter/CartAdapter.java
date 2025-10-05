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
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.elaboratocookaroo.Activity.CartActivity;
import com.example.elaboratocookaroo.Object.CartItem;
import com.example.elaboratocookaroo.R;

public class CartAdapter extends ArrayAdapter<CartItem> {
    private Context c;
    private CartItem[] items;
    private float total;
    public CartAdapter(Context c,CartItem[] items){
        super(c, R.layout.row_cart,R.id.name,items);
        this.c=c;
        this.items=items;
    }

    public float getTotal() {
        float tot=0;
        for(int i=0;i<items.length;i++)
            tot+=items[i].getSubtotal();
        return tot;
    }

    public CartItem[] getItems() {
        return items;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater) c.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=layoutInflater.inflate(R.layout.row_cart,parent,false);
        ImageView imageView=row.findViewById(R.id.image);
        TextView name=row.findViewById(R.id.name);
        TextView subtotal=row.findViewById(R.id.subtotal);
        ElegantNumberButton elegantNumberButton=row.findViewById(R.id.quantity);
        Glide.with(c).load(items[position].getImage()).into(imageView);
        name.setText(items[position].getName());
        subtotal.setText(Float.toString(items[position].getSubtotal()));
        total=total+items[position].getSubtotal();
        elegantNumberButton.setNumber(Integer.toString(items[position].getQuantity()));
        elegantNumberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                Float oldSubtotal=Float.parseFloat(subtotal.getText().toString());
                Float price=oldSubtotal/oldValue;
                Float newSubtotal=oldSubtotal+((newValue-oldValue)*price);
                subtotal.setText(String.format("%.02f",newSubtotal));
                total=total+(newSubtotal-oldSubtotal);
                CartActivity.total.setText("Totale: "+String.format("%.02f",total)+" £");
                items[position].setQuantity(newValue);
            }
        });
        return row;
    }
}

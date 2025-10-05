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
import com.example.elaboratocookaroo.Object.CartItem;
import com.example.elaboratocookaroo.R;

public class RiepilogoAdapter extends ArrayAdapter<CartItem> {
    private Context c;
    private CartItem[] items;
    public RiepilogoAdapter(Context c,CartItem[] items){
        super(c, R.layout.row_riepilogo,R.id.name,items);
        this.c=c;
        this.items=items;
    }
    public CartItem[] getItems() {
        return items;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater) c.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=layoutInflater.inflate(R.layout.row_riepilogo,parent,false);
        ImageView imageView=row.findViewById(R.id.image);
        TextView name=row.findViewById(R.id.name);
        TextView subtotal=row.findViewById(R.id.subtotal);
        Glide.with(c).load(items[position].getImage()).into(imageView);
        name.setText(items[position].getName());
        subtotal.setText((items[position].getQuantity())+" pezzi, subtotale: "+(items[position].getSubtotal()));
        return row;
    }
}

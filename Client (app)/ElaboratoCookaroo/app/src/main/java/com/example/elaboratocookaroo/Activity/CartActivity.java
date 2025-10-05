package com.example.elaboratocookaroo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.elaboratocookaroo.Adapter.CartAdapter;
import com.example.elaboratocookaroo.Common.Methods;
import com.example.elaboratocookaroo.Common.Variables;
import com.example.elaboratocookaroo.Object.Cart;
import com.example.elaboratocookaroo.R;
import com.example.elaboratocookaroo.Request.CartList;
import com.example.elaboratocookaroo.Request.DeleteCart;
import com.example.elaboratocookaroo.Request.DeleteSingleCart;
import com.example.elaboratocookaroo.Request.UpdateCart;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CartActivity extends AppCompatActivity {
    public static TextView total;
    public static CartAdapter cartAdapter;
    public static Activity activity;
    private boolean isEmpty;
    private SwipeMenuListView listView;
    private Cart[] carts;
    private FloatingActionButton deleteall,pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CartList r=new CartList("192.168.1.9",5000);
        Methods.SJ(r);
        if(r.getCartItems().length!=0){
            activity=this;
            isEmpty=false;
            setContentView(R.layout.activity_cart);
            total=findViewById(R.id.total);
            deleteall=findViewById(R.id.btnDeleteAll);
            pay=findViewById(R.id.btnCart);
            listView=findViewById(R.id.lv);
            cartAdapter=new CartAdapter(CartActivity.this,r.getCartItems());
            listView.setAdapter(cartAdapter);
            SwipeMenuCreator creator = new SwipeMenuCreator() {
                @Override
                public void create(SwipeMenu menu) {
                    SwipeMenuItem deleteItem = new SwipeMenuItem(
                            getApplicationContext());
                    deleteItem.setBackground(new ColorDrawable(Color.rgb(0xff, 0x00, 0x00)));
                    deleteItem.setWidth(170);
                    deleteItem.setIcon(R.drawable.ic_baseline_clear_24);
                    menu.addMenuItem(deleteItem);
                }
            };
            listView.setMenuCreator(creator);
            listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                    Cart c=new Cart(Variables.currentUser,cartAdapter.getItems()[position].getCodicep(),cartAdapter.getItems()[position].getQuantity());
                    DeleteSingleCart deleteSingleCart=new DeleteSingleCart("192.168.1.9",5000,c);
                    Methods.SJ(deleteSingleCart);
                    CartList cartList=new CartList("192.168.1.9",5000);
                    Methods.SJ(cartList);
                    cartAdapter=new CartAdapter(CartActivity.this,cartList.getCartItems());
                    listView.setAdapter(cartAdapter);
                    total.setText("Totale: "+String.format("%.02f",cartAdapter.getTotal())+" £");
                    return false;
                }
            });
            total.setText("Totale: "+String.format("%.02f",cartAdapter.getTotal())+" £");
            deleteall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DeleteCart r=new DeleteCart("192.168.1.9",5000);
                    Methods.SJ(r);
                    setContentView(R.layout.nothingyet);
                    TextView textView=findViewById(R.id.message);
                    textView.setText("Carrello vuoto!");
                }
            });
            pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Variables.totalCost=Float.parseFloat(String.format("%.02f",cartAdapter.getTotal()));
                    Intent intent=new Intent(CartActivity.this,CardActivity.class);
                    startActivity(intent);
                }
            });
        }
        else{
            isEmpty=true;
            setContentView(R.layout.nothingyet);
            TextView textView=findViewById(R.id.message);
            textView.setText("Carrello vuoto!");
        }
    }
    @Override
    public void onBackPressed(){
        if(!isEmpty){
            int size=cartAdapter.getItems().length;
            carts=new Cart[size];
            for(int i=0;i<size;i++)
                carts[i]=new Cart(Variables.currentUser,cartAdapter.getItems()[i].getCodicep(),cartAdapter.getItems()[i].getQuantity());
            UpdateCart r=new UpdateCart("192.168.1.9",5000,carts);
            Methods.SJ(r);
            super.onBackPressed();
        }
        else super.onBackPressed();
    }
}
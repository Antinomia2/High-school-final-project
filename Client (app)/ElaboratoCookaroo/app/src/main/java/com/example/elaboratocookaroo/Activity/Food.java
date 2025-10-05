package com.example.elaboratocookaroo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import com.example.elaboratocookaroo.Adapter.FoodAdapter;
import com.example.elaboratocookaroo.Common.Methods;
import com.example.elaboratocookaroo.Common.Variables;
import com.example.elaboratocookaroo.Object.FoodInfo.FoodItem;
import com.example.elaboratocookaroo.R;
import com.example.elaboratocookaroo.Request.FoodList;
import com.google.android.gms.common.internal.service.Common;

import java.util.ArrayList;

public class Food extends AppCompatActivity {
    private ListView lv;
    private FoodItem[] foodItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        Intent i=getIntent();
        String category=i.getStringExtra("tipologia");
        SearchBarInit();
        FoodList r=new FoodList("192.168.1.9",5000,category);
        Methods.SJ(r);
        foodItems=r.getFoodItems();
        lv=findViewById(R.id.lv);
        lv.setAdapter(new FoodAdapter(this,foodItems));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Variables.currentDish=(FoodItem)parent.getItemAtPosition(position);
                Intent intent=new Intent(Food.this,FoodDetail.class);
                startActivity(intent);
            }
        });
    }

    private void SearchBarInit() {
        SearchView sv=findViewById(R.id.shapeListSearchView);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<FoodItem> foodItemsFiltered=new ArrayList<FoodItem>();
                for(int i=0;i<foodItems.length;i++)
                    if(foodItems[i].getName().contains(newText))
                        foodItemsFiltered.add(foodItems[i]);
                FoodItem[] filters=new FoodItem[foodItemsFiltered.size()];
                for(int i=0;i<filters.length;i++)
                    filters[i]=foodItemsFiltered.get(i);
                lv.setAdapter(new FoodAdapter(getApplicationContext(),filters));
                return false;
            }
        });
    }
}
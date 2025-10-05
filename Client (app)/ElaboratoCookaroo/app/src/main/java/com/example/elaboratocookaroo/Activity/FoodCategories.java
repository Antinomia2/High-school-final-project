package com.example.elaboratocookaroo.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.elaboratocookaroo.Adapter.CategoryAdapter;
import com.example.elaboratocookaroo.Common.Methods;
import com.example.elaboratocookaroo.Common.Variables;
import com.example.elaboratocookaroo.Object.Category;
import com.example.elaboratocookaroo.R;
import com.example.elaboratocookaroo.Request.CategoryList;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.material.navigation.NavigationView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class FoodCategories extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_categories);
        CategoryList r=new CategoryList("192.168.1.9",5000);
        Methods.SJ(r);
        ListView listView=findViewById(R.id.lv);
        listView.setAdapter(new CategoryAdapter(this,r.getCategories()));
        drawerLayout=findViewById(R.id.drawer);
        navigationView=findViewById(R.id.nav);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Category category=(Category)parent.getItemAtPosition(position);
                Intent intent=new Intent(FoodCategories.this,Food.class);
                intent.putExtra("tipologia",category.getName());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch(item.getItemId()){
            case R.id.carrello:
                intent=new Intent(FoodCategories.this,CartActivity.class);
                startActivity(intent);
                break;
            case R.id.area:
                intent=new Intent(FoodCategories.this,DishesChart.class);
                startActivity(intent);
                break;
            case R.id.logout:
                intent=new Intent(FoodCategories.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.prenotazione:
                intent=new Intent(FoodCategories.this,PrenotaPosti.class);
                startActivity(intent);
                break;
            case R.id.sedi:
                intent=new Intent(FoodCategories.this,Locals.class);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
}
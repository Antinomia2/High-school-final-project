package com.example.elaboratocookaroo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.elaboratocookaroo.Common.Methods;
import com.example.elaboratocookaroo.Common.Variables;
import com.example.elaboratocookaroo.Dialog.MessageDialog;
import com.example.elaboratocookaroo.Object.Cart;
import com.example.elaboratocookaroo.Object.Valutation;
import com.example.elaboratocookaroo.R;
import com.example.elaboratocookaroo.Request.AddToCart;
import com.example.elaboratocookaroo.Request.CommentDish;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import java.util.Arrays;

public class FoodDetail extends AppCompatActivity implements RatingDialogListener {
    TextView food_name,food_price,food_description;
    ImageView food_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart,btnRating;
    ElegantNumberButton numberButton;
    RatingBar ratingBar;
    Button showcomment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        numberButton=(ElegantNumberButton)findViewById(R.id.number_button);
        btnCart=(FloatingActionButton)findViewById(R.id.btnCart);
        btnRating=findViewById(R.id.btnRating);
        ratingBar=findViewById(R.id.ratingBar);
        showcomment=findViewById(R.id.btnShowComment);
        food_description=(TextView)findViewById(R.id.food_description);
        food_name=(TextView)findViewById(R.id.food_name);
        food_price=(TextView)findViewById(R.id.food_price);
        food_image=findViewById(R.id.img_food);
        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collpsing);
        Glide.with(this).load(Variables.currentDish.getImage()).into(food_image);
        collapsingToolbarLayout.setTitle(Variables.currentDish.getName());
        food_price.setText(Float.toString(Variables.currentDish.getPrezzo()));
        food_name.setText(Variables.currentDish.getName());
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<Variables.currentDish.getIngredientArrayList().size();i++)
            sb=Variables.currentDish.getIngredientArrayList().get(i).getAllergeni()?sb.append(Variables.currentDish.getIngredientArrayList().get(i).getName()+" *\n"):sb.append(Variables.currentDish.getIngredientArrayList().get(i).getName()+"\n");
        food_description.setText("Ingredienti :\n"+sb.toString());
        ratingBar.setRating(Variables.currentDish.getAvg_valutazione());
        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddToCart r=new AddToCart("192.168.1.9",5000,new Cart(Variables.currentUser,Variables.currentDish.getCodicep(),Integer.parseInt(numberButton.getNumber())));
                Methods.SJ(r);
                new MessageDialog("Messaggio","Aggiunto al carrello!").show(getSupportFragmentManager(),"dialog");
            }
        });
        showcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FoodDetail.this,Comment.class);
                startActivity(intent);
            }
        });
    }
    private void showDialog() {
        new AppRatingDialog.Builder()
                .setPositiveButtonText("Invia")
                .setNegativeButtonText("Cancella")
                .setNoteDescriptions(Arrays.asList("Pessimo", "Dispiaciuto", "Benino", "Mi piace", "Eccellente!"))
                .setDefaultRating(0)
                .setTitle("Valuta questo piatto")
                .setDescription("Seleziona un voto di gradimento")
                .setCommentInputEnabled(true)
                .setHint("Lascia un commento...")
                .setCommentBackgroundColor(R.color.colorPrimaryDark)
                .setWindowAnimation(R.style.MyDialogFadeAnimation)
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .create(FoodDetail.this)
                .show();
    }

    @Override
    public void onNegativeButtonClicked() {

    }

    @Override
    public void onNeutralButtonClicked() {

    }

    @Override
    public void onPositiveButtonClicked(int i, String s) {
        CommentDish r=new CommentDish("192.168.1.9",5000,new Valutation(Variables.currentUser,Variables.currentDish.getCodicep(),s,i));
        Methods.SJ(r);
        ratingBar.setRating(r.getAvg_valutazione());
    }
}
package com.example.elaboratocookaroo.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;
import com.braintreepayments.cardform.view.CardForm;
import com.example.elaboratocookaroo.Common.Variables;
import com.example.elaboratocookaroo.Object.Order;
import com.example.elaboratocookaroo.R;
import com.example.elaboratocookaroo.Request.Checkout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import com.wdullaer.materialdatetimepicker.time.Timepoint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CardActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    CardForm cardForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance(CardActivity.this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                now.get(Calendar.SECOND),
                true
        );
        tpd.setMinTime(new Timepoint(Calendar.HOUR+1,Calendar.MINUTE));
        tpd.setMaxTime(new Timepoint(22,59));
        cardForm=findViewById(R.id.card_form);
        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .cardholderName(CardForm.FIELD_REQUIRED)
                .postalCodeRequired(true)
                .actionLabel("Purchase")
                .setup(CardActivity.this);
        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        FloatingActionButton pay=findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cardForm.isValid())
                    tpd.show(getSupportFragmentManager(), "Datepickerdialog");
                else
                    Toast.makeText(CardActivity.this, "Si prega di completare il form", Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        AlertDialog.Builder alertBuilder=new AlertDialog.Builder(CardActivity.this);
        alertBuilder.setTitle("Conferma prima di pagare");
        alertBuilder.setMessage("Numero carta: " + cardForm.getCardNumber() + "\n" +
                "Scadenza: " + cardForm.getExpirationDateEditText().getText().toString() + "\n" +
                "CVV: " + cardForm.getCvv() + "\n" +
                "Codice posta: " + cardForm.getPostalCode()+ "\n" +
                "Orario consegna: "+hourOfDay+":"+minute+":"+second);
        alertBuilder.setPositiveButton("Paga", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Order order=new Order(Variables.currentUser,new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date())+" "+hourOfDay+":"+minute+":"+second,Variables.totalCost,CartActivity.cartAdapter.getItems());
                Checkout r=new Checkout("192.168.1.9",5000,order);
                r.start();
                try {
                    r.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent=new Intent(CardActivity.this,Riepilogo.class);
                startActivity(intent);
                CartActivity.activity.finish();
                finish();
            }
        });
        alertBuilder.setNegativeButton("Cancella", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
    }
}
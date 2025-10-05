package com.example.elaboratocookaroo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.elaboratocookaroo.Common.Methods;
import com.example.elaboratocookaroo.Common.Variables;
import com.example.elaboratocookaroo.Dialog.MessageDialog;
import com.example.elaboratocookaroo.Object.Credentials;
import com.example.elaboratocookaroo.R;
import com.example.elaboratocookaroo.Request.Login;
import com.google.android.gms.common.internal.service.Common;

public class LoginActivity extends AppCompatActivity {
    public static EditText userid;
    private EditText pass;
    private TextView register;
    private CardView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userid=findViewById(R.id.editText);
        pass=findViewById(R.id.editText2);
        login=findViewById(R.id.cardView);
        register=findViewById(R.id.textView2);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Variables.currentUser=userid.getText().toString();
                String password= Methods.CifrarioCesare(pass.getText().toString(),3);
                Credentials credentials=new Credentials(Variables.currentUser,password);
                Login r=new Login("192.168.1.9",5000,credentials);
                Methods.SJ(r);
                if(r.isCorrect()) {
                    Intent intent=new Intent(LoginActivity.this, FoodCategories.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    new MessageDialog("Errore!","Password o Username non corretto").show(getSupportFragmentManager(),"dialog");
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}